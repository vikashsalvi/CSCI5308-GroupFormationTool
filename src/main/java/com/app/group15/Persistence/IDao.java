package com.app.group15.Persistence;



import java.util.List;

@SuppressWarnings("hiding")
public interface IDao<T> {

	<T extends Persistence> T get(int id);

	<T extends Persistence> List<T> getAll();

	<T extends Persistence> int save(T t);

	<T extends Persistence> void update(T t, int id);

	<T extends Persistence> void delete(int id);


}
