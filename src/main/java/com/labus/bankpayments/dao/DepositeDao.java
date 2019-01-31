package com.labus.bankpayments.dao;

import com.labus.bankpayments.entity.Deposite;
import com.labus.bankpayments.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepositeDao extends EntityDao<Integer,Deposite> {

    private final static String INSERT_DEPOSITE = "INSERT INTO bankschema.deposite (account_number, rate) VALUES (?, ?)";
    private final static String SELECT_ALL = "SELECT id, account_number, rate FROM bankschema.deposite";
    private final static String SELECT_BY_ID = "SELECT id, account_number, rate FROM bankschema.deposite WHERE id=?";
    private final static String DELETE_BY_ID = "DELETE FROM bankschema.deposite WHERE id=?";
    private final static String UPDATE_RATE = "UPDATE bankschema.deposite SET rate=? WHERE account_number=?";
    private final static String SELECT_BY_NUMBER = "SELECT id, account_number, rate FROM bankschema.deposite WHERE account_number=?";

    private static Logger logger=Logger.getLogger(String.valueOf(DepositeDao.class));
    @Override
    public List<Deposite> getAll() throws DaoException {
        List<Deposite> deposites = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Deposite deposite = retrieveEntity(resultSet);
                deposites.add(deposite);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return deposites;
    }

    @Override
    public Deposite getById(Integer id) throws DaoException {
        Deposite deposite = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                deposite = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return deposite;
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
    }

    @Override
    public Integer create(Deposite deposite) throws DaoException {
        int idDeposite = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT_DEPOSITE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(deposite.getAccount_number()));
            statement.setString(2, String.valueOf(deposite.getRate()));
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                idDeposite = generatedKey.getInt(1);
            }
            generatedKey.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return idDeposite;
    }

    @Override
    public void update(Deposite deposite) throws DaoException {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_RATE)) {
            statement.setString(1, String.valueOf(deposite.getRate()));
            statement.setString(2, String.valueOf(deposite.getAccount_number()));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
    }
    public Deposite getByNumber(Long number) throws DaoException {
        Deposite deposite = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_NUMBER)) {
            statement.setLong(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                deposite = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return deposite;

    }

    @Override
    public Deposite retrieveEntity(ResultSet resultSet) throws DaoException {
        Deposite deposite = new Deposite();
        try{
            deposite.setId(resultSet.getInt("id"));
            deposite.setAccount_number(resultSet.getLong("account_number"));
            deposite.setRate(resultSet.getShort("rate"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return deposite;
    }
}
