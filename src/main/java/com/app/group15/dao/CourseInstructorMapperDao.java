package com.app.group15.dao;


import com.app.group15.model.Course;
import com.app.group15.model.CourseInstructorMapper;
import com.app.group15.model.User;
import com.app.group15.persistence.DatabaseManager;
import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;


@SuppressWarnings("rawtypes")
public class CourseInstructorMapperDao extends CourseInstructorMapperAbstractDao {


	@Override
	public ArrayList<CourseInstructorMapper> getAll() {
		String query = "SELECT * from table_course_instructor_mapper";
		ArrayList<CourseInstructorMapper> allList = new ArrayList<CourseInstructorMapper>();
		try (Connection connection = DatabaseManager.getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				CourseInstructorMapper entity = new CourseInstructorMapper();
				entity.setId(result.getInt("id"));
				entity.setCourseId(result.getInt("course_id"));
				entity.setInstructorId(result.getInt("instructor_id"));
				entity.setTaId(result.getInt("ta_id"));
				allList.add(entity);
			}

		} catch (SQLException e) {

			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return allList;
	}

	@Override
	public User getCourseInstructor(int id) {
		String query = "SELECT * FROM table_users tu\n" +
			"JOIN table_course_instructor_mapper tcm ON tu.id=tcm.instructor_id\n" +
			"WHERE tcm.course_id=?";
		User userEntity = new User();
		try (Connection connection = DatabaseManager.getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					GroupFormationToolLogger.log(Level.INFO, result.getString("first_name"));
					userEntity.setFirstName(result.getString("first_name"));
					userEntity.setLastName(result.getString("last_name"));
					userEntity.setId(result.getInt("instructor_id"));
				}

			}
		} catch (SQLException e) {

			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return userEntity;
	}

	@Override
	public void deleteByCourseId(int courseId) {
		String query = "DELETE FROM table_course_instructor_mapper WHERE course_id=?";
		try (Connection connection = DatabaseManager.getDataSource().getConnection();
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
	protected boolean doesCourseIdExistInThisMapper(int courseId) {
		String query = "SELECT * FROM table_course_instructor_mapper WHERE course_id=?";
		try (Connection connection = DatabaseManager.getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, courseId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					return true;

				}
			}
		} catch (Exception e) {

			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return false;

	}

	@Override
	protected void addInstructorForCourseWithTa(int courseId, int instructorId) {
		String query = "UPDATE table_course_instructor_mapper SET instructor_id=? WHERE course_id=?";
		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
					GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				}
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Override
	protected void addTaForCourseWithInstructor(int courseId, int taId) {
		String query = "UPDATE table_course_instructor_mapper SET ta_id=? WHERE course_id=?";
		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
					GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				}
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Override
	public void addInstructorToACourse(int courseId, int instructorId) {
		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
						GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
					}
					GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				}

			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}


	}

	@Override
	public void addTaToACourse(int courseId, int taId) {
		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
						GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
					}
					GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				}

			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}


	}

	@Override
	public Course getCourseByTa(int taId){
		String query = "select * from table_course_instructor_mapper tim\n" +
			"Join table_course tc on tc.id = tim.course_id \n" +
			"where tim.ta_id = ?";
		Course course=new Course();

		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, taId);
				try (ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						course.setId(result.getInt("course_id"));
						course.setName(result.getString("name"));
					}
				}
			} catch (SQLException e) {
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return course;
	}

	@Override
	public ArrayList<Course> getCoursesByInstructor(int id) {
		String query = "select * from table_course_instructor_mapper tim\n" +
			"Join table_course tc on tc.id = tim.course_id \n" +
			"where tim.instructor_id = ?";
		ArrayList<Course> arrayListCourse = new ArrayList<Course>();

		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, id);
				try (ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						Course courseEntity = new Course();
						courseEntity.setId(result.getInt("course_id"));
						courseEntity.setName(result.getString("name"));
						arrayListCourse.add(courseEntity);
					}
				}
			} catch (SQLException e) {
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return arrayListCourse;
	}

	public User getCourseTA(int id) {
		String query = "SELECT * FROM table_users tu\n" +
			"JOIN table_course_instructor_mapper tcm ON tu.id=tcm.ta_id\n" +
			"WHERE tcm.course_id=?";
		User userEntity = new User();

		try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, id);
				try (ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						userEntity.setFirstName(result.getString("first_name"));
						userEntity.setLastName(result.getString("last_name"));
						userEntity.setId(result.getInt("ta_id"));
					}
				}
			} catch (SQLException e) {
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		return userEntity;
	}


}
