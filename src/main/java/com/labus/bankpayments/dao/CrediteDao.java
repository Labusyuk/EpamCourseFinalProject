package com.labus.bankpayments.dao;

import com.labus.bankpayments.entity.Credite;
import com.labus.bankpayments.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrediteDao extends EntityDao<Integer, Credite> {

    private final static String INSERT_CREDITE = "INSERT INTO bankschema.credite (account_number, limit, debt, amount_interest, rate) VALUES (?, ?, ?, ?, ?)";
    private final static String SELECT_ALL = "SELECT id, account_number, limit, debt, amount_interest, rate FROM bankschema.credite";
    private final static String SELECT_BY_ID = "SELECT id, account_number, limit, debt, amount_interest, rate FROM bankschema.credite WHERE id=?";
    private final static String DELETE_BY_ID = "DELETE FROM bankschema.credite WHERE id=?";
    private final static String UPDATE_DEBT_AMOUNTINTEREST = "UPDATE bankschema.credite SET debt=?, amount_interest=? WHERE account_number=?";
    private final static String SELECT_BY_NUMBER = "SELECT id, account_number, `limit`, debt, amount_interest, rate FROM bankschema.credite WHERE account_number=?";

    private static Logger logger=Logger.getLogger(String.valueOf(CrediteDao.class));
    @Override
    public List<Credite> getAll() throws DaoException {
        List<Credite> credites = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Credite credite = retrieveEntity(resultSet);
                credites.add(credite);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return credites;
    }

    @Override
    public Credite getById(Integer id) throws DaoException {
        Credite credite = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                credite = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return credite;
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
    public Integer create(Credite credite) throws DaoException {
        int idCredite = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT_CREDITE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(credite.getAccount_number()));
            statement.setString(2, String.valueOf(credite.getLimit()));
            statement.setString(3, String.valueOf(credite.getDebt()));
            statement.setString(4, String.valueOf(credite.getAmount_interest()));
            statement.setString(5, String.valueOf(credite.getRate()));
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                idCredite = generatedKey.getInt(1);
            }
            generatedKey.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return idCredite;
    }

    @Override
    public void update(Credite credite) throws DaoException {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_DEBT_AMOUNTINTEREST)) {
            statement.setString(1, String.valueOf(credite.getDebt()));
            statement.setString(2, String.valueOf(credite.getAmount_interest()));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
    }
    public Credite getByNumber(Long number) throws DaoException {
        Credite credite = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_NUMBER)) {
            statement.setLong(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                credite = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return credite;
    }

    @Override
    public Credite retrieveEntity(ResultSet resultSet) throws DaoException {
        Credite credite = new Credite();
        try{
            credite.setId(resultSet.getInt("id"));
            credite.setAccount_number(resultSet.getLong("account_number"));
            credite.setLimit(resultSet.getLong("limit"));
            credite.setDebt(resultSet.getLong("debt"));
            credite.setAmount_interest(resultSet.getLong("amount_interest"));
            credite.setRate(resultSet.getShort("rate"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return credite;
    }
}
