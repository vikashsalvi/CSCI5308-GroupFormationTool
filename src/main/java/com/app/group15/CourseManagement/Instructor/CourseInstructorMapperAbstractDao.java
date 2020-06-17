package com.app.group15.CourseManagement.Instructor;

import com.app.group15.CourseManagement.Course;
import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;
import com.app.group15.UserManagement.User;

import java.util.List;

public abstract class CourseInstructorMapperAbstractDao<T> implements IDao {

    public abstract User getCourseInstructor(int id);

    public abstract List<Course> getCourseByInstructor(int id);

    public abstract List<Course> getCoursesByInstructor(int taId);

	public abstract Course getCourseByTa(int id);

    public abstract void deleteByCourseId(int courseId);

    protected abstract boolean doesCourseIdExistInThisMapper(int courseId);

    protected abstract void addInstructorForCourseWithTa(int courseId, int instructorId);

    protected abstract void addTaForCourseWithInstructor(int courseId, int taId);

    public abstract void addInstructorToACourse(int courseId, int instructorId);

    public abstract void addTaToACourse(int courseId, int taId);

    @Override
    public abstract List getAll();

    @Override
    public int save(Persistence t) {
        return 0;
    }

    @Override
    public void update(Persistence t, int id) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Persistence get(int id) {
        return null;
    }

    public abstract User getCourseTA(int userId);


}
