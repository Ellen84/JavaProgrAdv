package be.pxl.student;

import be.pxl.student.bean.Account;
import be.pxl.student.bean.Payment;
import be.pxl.student.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BudgetPlanner {

	private String url;
	private String username;
	private String password;

	public BudgetPlanner(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * Enable transactions by disabling auto commit on the connection
	 * @throws BudgetPlannerException
	 */
	public void enableTransactions() throws BudgetPlannerException {
		try {
			getConnection ().setAutoCommit(false);
		} catch (SQLException e) {
			throw new BudgetPlannerException(e);
		}
	}

	private Connection getConnection() throws BudgetPlannerException {
		try {
			return ConnectionFactory.getConnection(url, username ,password);
		} catch (SQLException e) {
			throw new BudgetPlannerException(e);
		}
	}

	/**
	 * Rollback everything since last commit
	 * @throws BudgetPlannerException
	 */
	public void rollback() throws BudgetPlannerException {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new BudgetPlannerException(e);
		}
	}

	/**
	 * Close the database connection
	 */
	public void close() {
		ConnectionFactory.closeConnection();
	}

	/**
	 * Create a payment in the database without a label
	 * @param date
	 * @param amount
	 * @param currency
	 * @param detail
	 * @param account
	 * @param counterAccount
	 * @return
	 * @throws BudgetPlannerException
	 */
	public Payment createPayment(LocalDate date, float amount, String currency, String detail, Account account, Account counterAccount) throws BudgetPlannerException {
		return createPayment(date, amount, currency, detail, account.getId(), counterAccount.getId());
	}

	/**
	 * Create a payment in the database without a label
	 * @param date
	 * @param amount
	 * @param currency
	 * @param detail
	 * @param accountId
	 * @param counterAccountId
	 * @return
	 * @throws BudgetPlannerException
	 */
	public Payment createPayment(LocalDate date, float amount, String currency, String detail, int accountId, int counterAccountId) throws BudgetPlannerException {
		try {
			return PaymentDao.insertPayment(getConnection(),
					new Payment(
							date,
							amount,
							currency,
							detail,
							accountId,
							counterAccountId,
							-1
					)
			);
		} catch (PaymentException e) {
			throw new BudgetPlannerException(e);
		}
	}

	/**
	 * Retrieve the payment form the database by its unique id
	 * @param id
	 * @return The Payment
	 * @throws BudgetPlannerException
	 */
	public Payment getPaymentById (int id) throws BudgetPlannerException {
		try {
			return PaymentDao.getPaymentById (getConnection(), id);
		} catch (PaymentException e) {
			throw new BudgetPlannerException(e);
		}
	}


	/**
	 * Create an account in the database
	 * @param number
	 * @param IBAN
	 * @param name
	 * @return The created account
	 * @throws BudgetPlannerException
	 */
	public Account createAccount(String number, String IBAN, String name) throws BudgetPlannerException {
			return createAccount(new Account(number, IBAN, name));
	}

	/**
	 * Create an account in the database
	 * @param account
	 * @return The created account
	 * @throws BudgetPlannerException
	 */
	public Account createAccount(Account account) throws BudgetPlannerException {
		try {
			return AccountDao.insertAccount(getConnection(), account);
		} catch (AccountException e) {
			throw new BudgetPlannerException(e);
		}
	}

	/**
	 * Retrieve an account from the database by its unique id
	 * @param id
	 * @return
	 * @throws BudgetPlannerException
	 */
	public Account getAccountById(int id) throws BudgetPlannerException {
		try {
			return AccountDao.getAccountById(getConnection(), id);
		} catch (AccountException e) {
			throw new BudgetPlannerException(e);
		}
	}

	/**
	 * Retrieve the account by its account number
	 * @param accountNumber
	 * @return
	 * @throws BudgetPlannerException
	 */
	public Account getAccountByNumber(String accountNumber) throws BudgetPlannerException {
		try {
			return AccountDao.getAccountByNumber(getConnection(), accountNumber);
		} catch (AccountException e) {
			throw new BudgetPlannerException(e);
		}
	}

	/**
	 * Retrieve all payments from the database for the specified account number
	 * @param accountNumber
	 * @return A list of payments
	 */
	public List<Payment> getPaymentsByAccountNumber(String accountNumber) throws BudgetPlannerException {

		try {
			Account account = AccountDao.getAccountByNumber(getConnection(), accountNumber);
			return PaymentDao.getPaymentsByAccountId(getConnection(), account.getId());
		} catch (AccountException | PaymentException e) {
			throw new BudgetPlannerException(e);
		}
	}

}
