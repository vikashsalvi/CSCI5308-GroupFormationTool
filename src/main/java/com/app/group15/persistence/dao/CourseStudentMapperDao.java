package com.app.group15.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.app.group15.persistence.entity.CourseStudentMapperEntity;
import com.app.group15.persistence.entity.PersistenceEntity;

@SuppressWarnings("rawtypes")
public class CourseStudentMapperDao implements Dao {

	private Connection connection;
	private final static Logger LOGGER = Logger.getLogger(CourseInstructorMapperDao.class.getName());

	@Override
	public void injectConnection(Connection connection) {
		this.connection = connection;

	}

	public int addStudentToACourse(int courseId, int studentId) {
		String query = "INSERT INTO table_course_student_mapper(course_id,student_id) VALUES(?,?)";
		int courseStudentMapperId = 0;
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
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return courseStudentMapperId;
	}

	public void deletByCourseId(int courseId) {
		String query = "DELETE FROM table_course_student_mapper WHERE course_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, courseId);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public PersistenceEntity get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CourseStudentMapperEntity> getAll() {
		String query = "SELECT * from table_course_student_mapper";
		ArrayList<CourseStudentMapperEntity> allList = new ArrayList<CourseStudentMapperEntity>();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				CourseStudentMapperEntity entity = new CourseStudentMapperEntity();
				entity.setId(result.getInt("id"));
				entity.setCourseId(result.getInt("course_id"));
				entity.setStudentId(result.getInt("student_id"));

				allList.add(entity);
			}

		} catch (SQLException e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return allList;
	}

	public ArrayList<Integer> getCourseIdsOfAStudent(int studentId) {
		String query = "SELECT * from table_course_student_mapper WHERE student_id=?";
		ArrayList<Integer> courseIds = new ArrayList<Integer>();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, studentId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {

					courseIds.add(Integer.valueOf(result.getInt("course_id")));

				}
			}
		} catch (SQLException e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return courseIds;

	}

	@Override
	public int save(PersistenceEntity t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(PersistenceEntity t, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
