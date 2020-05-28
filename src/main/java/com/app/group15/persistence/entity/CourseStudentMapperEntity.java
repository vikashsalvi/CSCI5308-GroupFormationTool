package com.app.group15.persistence.entity;

public class CourseStudentMapperEntity extends PersistenceEntity {
	private int courseId;
	private int studentId;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
