package com.app.group15.courseManagement;

import com.app.group15.persistence.IDao;
import com.app.group15.persistence.Persistence;

import java.util.ArrayList;


public abstract class CourseAbstractDao<T> implements IDao {
    @Override
    public abstract Persistence get(int id);

    @Override
    public abstract ArrayList<Course> getAll();

    @Override
    public abstract int save(Persistence persistence);

    @Override
    public abstract void update(Persistence persistence, int id);

    @Override
    public abstract void delete(int id);
}
