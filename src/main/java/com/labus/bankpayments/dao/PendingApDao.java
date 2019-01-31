package com.labus.bankpayments.dao;

import com.labus.bankpayments.entity.PendingAp;
import com.labus.bankpayments.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PendingApDao extends EntityDao<Integer,PendingAp> {

    private final static String INSERT_PENDINGAP = "INSERT INTO bankschema.pending_ap (login, date) VALUES (?, ?)";
    private final static String SELECT_ALL = "SELECT id, login, date FROM bankschema.pending_ap";
    private final static String SELECT_BY_ID = "SELECT id, login, date FROM bankschema.pending_ap WHERE id=?";
    private final static String DELETE_BY_ID = "DELETE FROM bankschema.pending_ap WHERE id=?";

    @Override
    public List<PendingAp> getAll() throws DaoException {
        List<PendingAp> pendingAps = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                PendingAp pendingAp = retrieveEntity(resultSet);
                pendingAps.add(pendingAp);
            }
            resultSet.close();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
        return pendingAps;
    }

    @Override
    public PendingAp getById(Integer id) throws DaoException {
        PendingAp pendingAp = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pendingAp = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
        return pendingAp;
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
    }

    @Override
    public Integer create(PendingAp pendingAp) throws DaoException {
        int idPendingAp = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT_PENDINGAP, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(pendingAp.getLogin()));
            statement.setString(2, String.valueOf(pendingAp.getDate()));
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                idPendingAp = generatedKey.getInt(1);
            }
            generatedKey.close();
        } catch (SQLException e) {
        }finally {
            returnConnection(connection);
        }
        return idPendingAp;
    }

    @Override
    public void update(PendingAp entity) throws DaoException {
        System.out.println("error");
    }

    @Override
    public PendingAp retrieveEntity(ResultSet resultSet) throws DaoException {
        PendingAp pendingAp = new PendingAp();
        try{
            pendingAp.setId(resultSet.getInt("id"));
            pendingAp.setLogin(resultSet.getString("login"));
            pendingAp.setDate(resultSet.getDate("date"));
        } catch (SQLException e) {
        }
        return pendingAp;
    }
}
