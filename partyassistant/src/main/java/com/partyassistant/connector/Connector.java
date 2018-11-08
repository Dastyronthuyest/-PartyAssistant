package com.partyassistant.connector;

import com.partyassistant.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connector {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/party?useSSL=false";

    private static Connection connection;

    private static Connector ourInstance;

    public static Connector getInstance() throws SQLException {
        if (ourInstance == null) {
            ourInstance = new Connector();
        }
        return ourInstance;
    }

    private Connector() throws SQLException {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public <T> T queryForObject(String sql, Mapper<T> mapper) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return mapper.mapRow(resultSet);
    }

    public <T> List<T> query(String sql, Mapper<T> mapper) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<T> resultList = new ArrayList<>();
        while(resultSet.next()){
            T newT = mapper.mapRow(resultSet);
            resultList.add(newT);
        }
        return resultList;
    }
}
