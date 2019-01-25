package com.labus.bankpayments.dao;

import com.labus.bankpayments.entity.Account;
import com.labus.bankpayments.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends EntityDao<Integer, Account> {

    private final static String INSERT_ACCOUNT = "INSERT INTO bankschema.accounts (number, type, owner, balance, validity) VALUES (?, ?, ?, ?, ?)";
    private final static String SELECT_ALL = "SELECT id, number, type, owner, balance, validity FROM bankschema.accounts";
    private final static String SELECT_BY_ID = "SELECT id, number, type, owner, balance, validity FROM bankschema.accounts WHERE id=?";
    private final static String DELETE_BY_ID = "DELETE FROM bankschema.accounts WHERE id=?";
    private final static String UPDATE_BALANCE = "UPDATE bankschema.accounts SET balance=? WHERE number=?";
    private final static String SELECT_BY_NUMBER = "SELECT id, number, type, owner, balance, validity FROM bankschema.accounts WHERE number=?";
    private final static String SELECT_BY_OWNER = "SELECT id, number, type, owner, balance, validity FROM bankschema.accounts WHERE owner=?";


    @Override
    public List<Account> getAll() throws DaoException {
        List<Account> accounts = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Account account = retrieveEntity(resultSet);
                accounts.add(account);
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        return accounts;
    }

    @Override
    public Account getById(Integer id) throws DaoException {
        Account account = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        return account;
    }
    public Account getByNumber(Long number) throws DaoException {
        Account account = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_NUMBER)) {
            statement.setLong(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        return account;
    }

    public Account getByOwner(String owner) throws DaoException {
        Account account = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_OWNER)) {
            statement.setString(1, owner);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        return account;
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Integer create(Account account) throws DaoException {
        int idAccount = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(account.getNumber()));
            statement.setString(2, String.valueOf(account.getType()));
            statement.setString(3, String.valueOf(account.getOwner()));
            statement.setString(4, String.valueOf(account.getBalance()));
            statement.setString(5, String.valueOf(account.getValidity()));
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                idAccount = generatedKey.getInt(1);
            }
            generatedKey.close();
        } catch (SQLException e) {
        }
        return idAccount;
    }

    @Override
    public void update(Account account) throws DaoException {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE)) {
            statement.setString(1, String.valueOf(account.getBalance()));
            statement.setString(2, String.valueOf(account.getNumber()));
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Account retrieveEntity(ResultSet resultSet) throws DaoException {
        Account account = new Account();
        try{
            account.setId(resultSet.getInt("id"));
            account.setNumber(resultSet.getLong("number"));
            account.setType(resultSet.getShort("type"));
            account.setOwner(resultSet.getString("owner"));
            account.setBalance(resultSet.getLong("balance"));
            account.setValidity(resultSet.getDate("validity"));
        } catch (SQLException e) {
        }
        return account;
    }
}
