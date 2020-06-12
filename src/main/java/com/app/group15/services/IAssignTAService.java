package com.app.group15.services;

public interface IAssignTAService {
	public boolean performTAUpdate(String bannerId, int courseId);
	public  boolean validateBannerID(String bannerId);
	public boolean validateCourseID(int courseId);
	public boolean checkIntructorPermission(int instructorId, int courseId);

}
