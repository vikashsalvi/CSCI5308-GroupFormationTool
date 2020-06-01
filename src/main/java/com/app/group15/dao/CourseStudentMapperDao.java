package com.app.group15.dao;


import com.app.group15.model.CourseStudentMapper;
import com.app.group15.model.Persistence;
import com.app.group15.persistence.DatabaseManager;
import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;


@SuppressWarnings("rawtypes")
public class CourseStudentMapperDao implements Dao {

	public int addStudentToACourse(int courseId, int studentId) {
		String query = "INSERT INTO table_course_student_mapper(course_id,student_id) VALUES(?,?)";
		int courseStudentMapperId = 0;
		try(Connection connection=DatabaseManager.getConnection()){
			try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				connection.setAutoCommit(false);
				statement.setInt(1, courseId);
				statement.setInt(2, studentId);
				statement.executeUpdate();
				try (ResultSet result = statement.getGeneratedKeys()) {

					if (result.first()) {

						courseStudentMapperId = result.getInt(1);
					}
				}

				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				}
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return courseStudentMapperId;
	}

	public void deletByCourseId(int courseId) {
		String query = "DELETE FROM table_course_student_mapper WHERE course_id=?";
		try (Connection connection=DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, courseId);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public Persistence get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CourseStudentMapper> getAll() {
		String query = "SELECT * from table_course_student_mapper";
		ArrayList<CourseStudentMapper> allList = new ArrayList<CourseStudentMapper>();
		try (Connection connection=DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				CourseStudentMapper entity = new CourseStudentMapper();
				entity.setId(result.getInt("id"));
				entity.setCourseId(result.getInt("course_id"));
				entity.setStudentId(result.getInt("student_id"));

				allList.add(entity);
			}

		} catch (SQLException e) {

			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return allList;
	}

	public ArrayList<Integer> getCourseIdsOfAStudent(int studentId) {
		String query = "SELECT * from table_course_student_mapper WHERE student_id=?";
		ArrayList<Integer> courseIds = new ArrayList<Integer>();
		try (Connection connection=DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, studentId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {

					courseIds.add(Integer.valueOf(result.getInt("course_id")));

				}
			}
		} catch (SQLException e) {

			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return courseIds;

	}

	@Override
	public int save(Persistence t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Persistence t, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
