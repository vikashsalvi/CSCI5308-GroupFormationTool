package com.app.group15.CourseManagement;

import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

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
