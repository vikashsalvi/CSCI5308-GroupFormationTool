package com.app.group15.dao;

import com.app.group15.model.Persistence;

import java.util.List;

public abstract class CourseAbstractDao<T> implements Dao {
    @Override
    public abstract Persistence get(int id);

    @Override
    public abstract List<T> getAll();

    @Override
    public abstract int save(Persistence persistence);

    @Override
    public abstract void update(Persistence persistence, int id);

    @Override
    public abstract void delete(int id);
}
