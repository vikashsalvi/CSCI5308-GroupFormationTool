package com.app.group15.persistence;

import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class InvokeStoredProcedure {

    private Connection connection;
    private String procedureName;
    private CallableStatement statement;


    public InvokeStoredProcedure(String procedureName) throws SQLException {
        this.procedureName = procedureName != null ? procedureName : null;
        prepareConnection();
        prepareStatement();
    }

    public void prepareConnection() throws SQLException {
        connection = DatabaseManager.getDataSource().getConnection();
    }

    public void prepareStatement() throws SQLException {
        statement = connection.prepareCall("{call " + procedureName + " }");
    }

    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        } catch (Exception e) {

            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void setParameter(int paramIndex, String value) throws SQLException {
        statement.setString(paramIndex, value);
    }

    public void setParameter(int paramIndex, long value) throws SQLException {
        statement.setLong(paramIndex, value);
    }

    public void registerOutputParameterLong(int paramIndex) throws SQLException {
        statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
    }

    public ResultSet executeWithResults() throws SQLException {
        if (statement.execute()) {
            return statement.getResultSet();
        }
        return null;
    }

    public void execute() throws SQLException {
        statement.execute();
    }

}
