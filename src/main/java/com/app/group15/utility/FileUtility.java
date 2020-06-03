package com.app.group15.utility;

import com.app.group15.ExceptionHandler.InvalidFileFormatException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;

public class FileUtility {
	public static boolean checkFileExist(String path) {
		if (Files.exists(Paths.get(path))) {
			return true;
		} else {
			return false;
		}
	}

	public static void createDirectory(String path) {
		try {
			Files.createDirectories(Paths.get(path));
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static void createFileIfNotExist(String path) {
		if (checkFileExist(path)) {
			createDirectory(path);
		}
	}

	public static ArrayList<HashMap<String, String>> readCSV(MultipartFile file, String[] cols) {
		ArrayList<HashMap<String, String>> data = new ArrayList<>();
		HashMap<String, String> dataRow;
		HashMap<String, String> error;
		String errorMessage = new String("");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String completeData = new String(bytes);
				String[] rows = completeData.split("\n");
				String[] fileCols = rows[0].toLowerCase().split(",");
				int i, j;
				if (Arrays.equals(fileCols, cols)) {
					for (i = 1; i < rows.length; i++) {
						j = 0;
						String[] cells = rows[i].split(",");
						dataRow = new HashMap<String, String>();
						for (String cell : cells) {
							dataRow.put(cols[j], cell);
							j++;
						}
						data.add(dataRow);
					}
				} else {
					throw new InvalidFileFormatException("Invalid File Structure");
				}

			} catch (IOException | InvalidFileFormatException e) {
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				error = new HashMap<String, String>();
				errorMessage = e.getMessage();
				error.put("error", errorMessage);
				data.add(error);
				return data;
			} finally {
				if (errorMessage.length() > 0) {
					error = new HashMap<String, String>();
					error.put("error", errorMessage);
					data.add(error);
					return data;
				}
			}
		}
		return data;
	}
}
