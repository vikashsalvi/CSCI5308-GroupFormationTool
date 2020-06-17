package com.app.group15.courseManagement;

import com.app.group15.persistence.IDao;
import com.app.group15.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public abstract class CourseStudentMapperAbstractDao<T> implements IDao {


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
