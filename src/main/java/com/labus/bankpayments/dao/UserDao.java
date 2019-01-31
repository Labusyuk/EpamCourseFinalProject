package com.labus.bankpayments.dao;

import com.labus.bankpayments.entity.User;
import com.labus.bankpayments.entity.UserRole;
import com.labus.bankpayments.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends EntityDao<Integer, User> {
    private final static String SELECT_BY_LOG_AND_PASS = "SELECT id, login, password, role, name_first, name_last FROM bankschema.users WHERE login=? AND password=?";
    private final static String INSERT_USER = "INSERT INTO bankschema.users (login, password, role, name_first, name_last) VALUES (?, ?, ?, ?, ?)";
    private final static String SELECT_ALL = "SELECT id, login, password, role, name_first, name_last FROM bankschema.users";
    private final static String SELECT_BY_ID = "SELECT id, login, password, role, name_first, name_last FROM bankschema.users WHERE id=?";
    private final static String DELETE_BY_ID = "DELETE FROM bankschema.users WHERE id=?";
    private final static String UPDATE_BY_LOGIN = "UPDATE bankschema.users SET password=?, role=?, name_first=?, name_last=? WHERE login=?";

    @Override
    public List<User> getAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = retrieveEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
        return users;
    }

    @Override
    public User getById(Integer id) throws DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException();
        }finally {
            returnConnection(connection);
        }
        return user;
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        try (
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
    }

    @Override
    public Integer create(User user) throws DaoException {
        int idUser = 0;
        try (
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, String.valueOf(user.getRole()));
            statement.setString(4, user.getName_first());
            statement.setString(5, user.getName_last());
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                idUser = generatedKey.getInt(1);
            }
            generatedKey.close();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
        return idUser;
    }

    @Override
    public void update(User user) throws DaoException {
        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_BY_LOGIN)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, String.valueOf(user.getRole()));
            statement.setString(3, user.getName_first());
            statement.setString(4, user.getName_last());
            statement.setString(5, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
    }

    public User retrieveEntity(ResultSet resultSet){
        User user = new User();
        try{
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(UserRole.valueOf(resultSet.getString("ROLE")));
            user.setName_first(resultSet.getString("name_first"));
            user.setName_last(resultSet.getString("name_last"));
        } catch (SQLException e) {
        }
        return user;
    }

    public User getByLogAndPass(String login, String password){
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOG_AND_PASS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
        return user;
    }
}
