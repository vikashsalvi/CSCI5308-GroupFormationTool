package com.app.group15.persistence.dao;

import java.util.List;

import com.app.group15.persistence.entity.PersistenceEntity;
import com.app.group15.persistence.injectors.ConnectionInjector;

@SuppressWarnings("hiding")
public interface Dao<T> extends ConnectionInjector {

	<T extends PersistenceEntity> T get(int id);

	<T extends PersistenceEntity> List<T> getAll();

	<T extends PersistenceEntity> int save(T t);

	<T extends PersistenceEntity> void update(T t, int id);

	<T extends PersistenceEntity> void delete(int id);

}
