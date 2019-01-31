package com.labus.bankpayments.dao;

import com.labus.bankpayments.entity.Payment;
import com.labus.bankpayments.exception.DaoException;
import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao extends EntityDao<Integer,Payment> {

    private final static String INSERT_PAYMENT= "INSERT INTO bankschema.payments (account_number, to, actions, amount, description, date) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT_ALL = "SELECT id, account_number, `to`, actions, amount, description, date FROM bankschema.payments";
    private final static String SELECT_BY_ID = "SELECT id, account_number, `to`, actions, amount, description, date FROM bankschema.payments WHERE id=?";
    private final static String DELETE_BY_ID = "DELETE FROM bankschema.payments WHERE id=?";
    private final static String SELECT_BY_ACCNUMBER = "SELECT id, account_number, `to`, actions, amount, description, `date` FROM bankschema.payments WHERE account_number=? OR `to`=?";
    private final static String SELECT_BY_TO = "SELECT id, account_number, `to`, actions, amount, description, date FROM bankschema.payments WHERE to=?";


    @Override
    public List<Payment> getAll() throws DaoException {
        List<Payment> payments = new ArrayList<>();
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Payment payment = retrieveEntity(resultSet);
                payments.add(payment);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return payments;
    }

    @Override
    public Payment getById(Integer id) throws DaoException {
        Payment payment = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = retrieveEntity(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return payment;
    }

    public List<Payment> getByNumber(long number) throws DaoException {
        List<Payment> payments = new ArrayList<>();
        Payment payment = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ACCNUMBER)) {
            statement.setLong(1, number);
            statement.setLong(2, number);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                payment = retrieveEntity(resultSet);
                payments.add(payment);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return payments;
    }


    @Override
    public void deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }finally {
            returnConnection(connection);
        }
    }

    @Override
    public Integer create(Payment payment) throws DaoException {
        int idPayment = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(payment.getAccount_number()));
            statement.setString(2, String.valueOf(payment.getTo()));
            statement.setString(3, String.valueOf(payment.getActions()));
            statement.setString(4, String.valueOf(payment.getAmount()));
            statement.setString(5, String.valueOf(payment.getDescription()));
            statement.setString(6, String.valueOf(payment.getDate()));
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                idPayment = generatedKey.getInt(1);
            }
            generatedKey.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }finally {
            returnConnection(connection);
        }
        return idPayment;
    }

    @Override
    public void update(Payment entity) throws DaoException {
        logger.log(Level.ERROR, "Информацию о платежах запрещенно изменять");
    }

    @Override
    public Payment retrieveEntity(ResultSet resultSet) throws DaoException {
        Payment payment = new Payment();
        try{
            payment.setId(resultSet.getInt("id"));
            payment.setAccount_number(resultSet.getLong("account_number"));
            payment.setTo(resultSet.getLong("to"));
            payment.setActions(resultSet.getByte("actions"));
            payment.setAmount(resultSet.getLong("amount"));
            payment.setDescription(resultSet.getString("description"));
            payment.setDate(resultSet.getDate("date"));
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        return payment;
    }
}
