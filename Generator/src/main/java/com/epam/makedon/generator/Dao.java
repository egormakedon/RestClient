package com.epam.makedon.generator;

import java.sql.*;
import java.util.Map;

final class Dao {
    private static final Dao INSTANCE = new Dao();
    private Dao() {}
    public static Dao getInstance() { return INSTANCE; }

    private static final String SQL_INSERT_AUTHOR = "INSERT INTO author(name,surname,country) VALUES(?,?,?)";
    private static final String SQL_SELECT_LAST_AUTHOR = "SELECT * FROM author ORDER BY author_id DESC LIMIT 1";
    private static final String SQL_INSERT_ARTICLE = "INSERT INTO article(title,body,date,fk_author_id,fk_resource_id) VALUES(?,?,?,?,?)";

    public int addAuthor(Map<String, String> parameters) {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        Statement st = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_INSERT_AUTHOR);

            String name = parameters.get("name");
            String surname = parameters.get("surname");
            String country = parameters.get("country");

            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, country);
            statement.executeUpdate();

            st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_LAST_AUTHOR);
            resultSet.next();
            return resultSet.getInt("author_id");
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            close(st);
            close(statement);
            close(connection);
        }
    }

    public void addArticle(Map<String, Object> parameters) {
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_INSERT_ARTICLE);

            String title = (String) parameters.get("title");
            String body = (String) parameters.get("body");
            Date date = (Date) parameters.get("date");
            int resource_id = (int) parameters.get("resource_id");
            int author_id = (int) parameters.get("author_id");

            statement.setString(1, title);
            statement.setString(2, body);
            statement.setDate(3, date);
            statement.setInt(4, author_id);
            statement.setInt(5, resource_id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            close(statement);
            close(connection);
        }
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                //
            }
        }
    }
    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //
            }
        }
    }
}
