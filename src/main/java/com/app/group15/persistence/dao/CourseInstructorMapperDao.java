package com.app.group15.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.app.group15.persistence.entity.CourseInstructorMapperEntity;
import com.app.group15.persistence.entity.PersistenceEntity;

@SuppressWarnings("rawtypes")
public class CourseInstructorMapperDao implements Dao {

	private Connection connection;

	private final static Logger LOGGER = Logger.getLogger(CourseInstructorMapperDao.class.getName());

	@Override
	public void injectConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public PersistenceEntity get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CourseInstructorMapperEntity> getAll() {
		String query = "SELECT * from table_course_instructor_mapper";
		ArrayList<CourseInstructorMapperEntity> allList = new ArrayList<CourseInstructorMapperEntity>();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				CourseInstructorMapperEntity entity = new CourseInstructorMapperEntity();
				entity.setId(result.getInt("id"));
				entity.setCourseId(result.getInt("course_id"));
				entity.setInstructorId(result.getInt("instructor_id"));
				entity.setTaId(result.getInt("ta_id"));
				allList.add(entity);
			}

		} catch (SQLException e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return allList;
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

	public void deleteByCourseId(int courseId) {
		String query = "DELETE FROM table_course_instructor_mapper WHERE course_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, courseId);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	private boolean doesCourseIdExistInThisMapper(int courseId) {
		String query = "SELECT * FROM table_course_instructor_mapper WHERE course_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, courseId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					return true;

				}
			}
		} catch (Exception e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return false;

	}

	private void addInstructorForCourseWithTa(int courseId, int instructorId) {
		String query = "UPDATE table_course_instructor_mapper SET instructor_id=? WHERE course_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, instructorId);
			statement.setInt(2, courseId);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void addTaForCourseWithInstructor(int courseId, int taId) {
		String query = "UPDATE table_course_instructor_mapper SET ta_id=? WHERE course_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, taId);
			statement.setInt(2, courseId);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void addInstructorToACourse(int courseId, int instructorId) {
		if (doesCourseIdExistInThisMapper(courseId)) {
			addInstructorForCourseWithTa(courseId, instructorId);

		} else {
			String query = "INSERT INTO table_course_instructor_mapper(course_id,instructor_id) VALUES(?,?)";

			try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				connection.setAutoCommit(false);
				statement.setInt(1, courseId);
				statement.setInt(2, instructorId);
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}

		}

	}

	public void addTaToACourse(int courseId, int taId) {
		if (doesCourseIdExistInThisMapper(courseId)) {
			addTaForCourseWithInstructor(courseId, taId);

		} else {
			String query = "INSERT INTO table_course_instructor_mapper(course_id,ta_id) VAUES(?,?)";

			try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				connection.setAutoCommit(false);
				statement.setInt(1, courseId);
				statement.setInt(2, taId);
				statement.executeUpdate();

				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}

		}

	}

}
