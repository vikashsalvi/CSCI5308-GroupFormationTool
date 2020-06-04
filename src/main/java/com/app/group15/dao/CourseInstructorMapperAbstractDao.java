package com.app.group15.dao;

import com.app.group15.model.Course;
import com.app.group15.model.Persistence;
import com.app.group15.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class CourseInstructorMapperAbstractDao<T> implements Dao {

    public abstract User getCourseInstructor(int id);
    
    public abstract ArrayList<Course> getCourseByInstructor(int id);

    public abstract ArrayList<Course> getCoursesByInstructor(int taId);

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

    @Override
    public Persistence get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

	public abstract User getCourseTA(int id);


}
