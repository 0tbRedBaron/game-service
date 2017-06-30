package com.myproject.gameapp.dao.postgreSql;

import com.myproject.gameapp.dao.UserDao;
import com.myproject.gameapp.dao.utils.JdbcUtils;
import com.myproject.gameapp.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Champion on 01.05.2017.
 */
public class PostgreSqlUserDao implements UserDao {

    private DataSource dataSource;

    public PostgreSqlUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User read(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";

        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = resultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("User select exception");
            e.printStackTrace();
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        return user;
    }

    public void create(User newUser) {

        String sql = "INSERT INTO users (first_name, login, password, email) VALUES (?, ?, ?, ?);";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getLogin());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setString(4, newUser.getEmail());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("User insert exception");
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    public void update(User theUser) {

        String sql = "UPDATE users SET first_name = ?, login = ?, password = ?, email = ? WHERE id = ?;";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theUser.getName());
            preparedStatement.setString(2, theUser.getLogin());
            preparedStatement.setString(3, theUser.getPassword());
            preparedStatement.setString(4, theUser.getEmail());
            preparedStatement.setInt(5, theUser.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("User update exception");
            e.printStackTrace();
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    private User resultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("first_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));

        return user;

    }
}
