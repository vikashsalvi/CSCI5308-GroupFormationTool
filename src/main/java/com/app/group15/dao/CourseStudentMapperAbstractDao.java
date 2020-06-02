package com.app.group15.dao;

import com.app.group15.model.Persistence;

import java.util.ArrayList;
import java.util.List;

public abstract class CourseStudentMapperAbstractDao<T> implements Dao {


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

    public abstract int addStudentToACourse(int courseId, int studentId);

    public abstract void deletByCourseId(int courseId);

    @Override
    public abstract List<T> getAll();

    public abstract ArrayList<Integer> getCourseIdsOfAStudent(int studentId);
}
