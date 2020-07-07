package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

import java.sql.SQLException;
import java.util.List;

public abstract class SurveyAbstractDao<T> implements IDao {
	@Override
	public abstract Persistence get(int id) throws SQLException, AwsSecretsManagerException;

	@Override
	public abstract List getAll() throws SQLException, AwsSecretsManagerException;

	@Override
	public abstract int save(Persistence persistence) throws SQLException, AwsSecretsManagerException;

	@Override
	public abstract void update(Persistence persistence, int id) throws SQLException, AwsSecretsManagerException;

	@Override
	public abstract void delete(int id) throws SQLException, AwsSecretsManagerException;
}
