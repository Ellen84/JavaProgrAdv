package be.pxl.student.dao;

import be.pxl.student.bean.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {

	public static Payment insertPayment (Connection connection, Payment payment) throws PaymentException {

		String query = "INSERT INTO Payment (`date`,`amount`,`currency`,`detail`,`accountId`,`counterAccountId`,`labelId`) VALUES (?,?,?,?,?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setObject(1, payment.getDate());
			stmt.setFloat(2, payment.getAmount());
			stmt.setString(3, payment.getCurrency());
			stmt.setString(4, payment.getDetail());
			stmt.setInt(5,payment.getAccountId());
			stmt.setInt(6, payment.getCounterAccountId());
			stmt.setInt(7, payment.getLabelId());

			stmt.executeUpdate();
			try (ResultSet resultSet = stmt.getGeneratedKeys()) {
				if (resultSet.next()) {
					payment.setId (resultSet.getInt(1));
				}
			}

			return payment;

		} catch (SQLException e) {
			throw new PaymentException(e);
		}

	}


	public static Payment getPaymentById(Connection connection, int id) throws PaymentException {
		String query = "SELECT `id`, `date`,`amount`,`currency`,`detail`,`accountId`,`counterAccountId`,`labelId` FROM Payment WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					return new Payment (
							resultSet.getInt("id"),
							resultSet.getDate("date").toLocalDate(),
							resultSet.getFloat("amount"),
							resultSet.getString("currency"),
							resultSet.getString("detail"),
							resultSet.getInt("accountId"),
							resultSet.getInt("counterAccountId"),
							resultSet.getInt("labelId"));
				}
			}
			throw new PaymentException(String.format("Payment with id [%s] not found",id));
		} catch (SQLException e) {
			throw new PaymentException(e);
		}
	}

	public static List<Payment> getPaymentsByAccountId(Connection connection, int accountId) throws PaymentException {
		String query = "SELECT `id`, `date`,`amount`,`currency`,`detail`,`accountId`,`counterAccountId`,`labelId` FROM Payment WHERE accountId = ?";

		List<Payment> payments = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, accountId);
			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					payments.add(new Payment (
							resultSet.getInt("id"),
							resultSet.getDate("date").toLocalDate(),
							resultSet.getFloat("amount"),
							resultSet.getString("currency"),
							resultSet.getString("detail"),
							resultSet.getInt("accountId"),
							resultSet.getInt("counterAccountId"),
							resultSet.getInt("labelId")));
				}
			}
		} catch (SQLException e) {
			throw new PaymentException(e);
		}

		return payments;
	}
}
