package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

import java.sql.SQLException;
import java.util.List;

public abstract class SurveyAbstractDao<T> implements IDao {
    @Override
    public Persistence get(int id) throws SQLException, AwsSecretsManagerException {
        return null;
    }

    @Override
    public List getAll() throws SQLException, AwsSecretsManagerException {
        return null;
    }

    @Override
    public int save(Persistence persistence) throws SQLException, AwsSecretsManagerException {
        return 0;
    }

    @Override
    public void update(Persistence persistence, int id) throws SQLException, AwsSecretsManagerException {

    }

    @Override
    public void delete(int id) throws SQLException, AwsSecretsManagerException {

    }

    public abstract Survey getSurvey(int id) throws SQLException, AwsSecretsManagerException;

    public abstract Survey getSurveyByCourseID(int courseID) throws SQLException, AwsSecretsManagerException;

    public abstract int saveSurvey(Survey survey) throws SQLException, AwsSecretsManagerException;
}
