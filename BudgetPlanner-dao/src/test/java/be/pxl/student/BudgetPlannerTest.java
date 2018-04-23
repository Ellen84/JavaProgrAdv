package be.pxl.student;

import be.pxl.student.bean.Account;
import be.pxl.student.bean.Payment;
import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Ignore
public class BudgetPlannerTest {

	private BudgetPlanner budgetPlanner;

	@Before
	public void setUp() throws Exception {
		budgetPlanner = new BudgetPlanner("jdbc:mysql://192.168.33.22/budgetplanner", "budgetplanner", "budgetplanner");
		budgetPlanner.enableTransactions();
	}

	private Account createTestAccount() throws BudgetPlannerException {
		return budgetPlanner.createAccount("123-1234567-12", "BE12 1234 1234 1234", "Random Test Account");
	}
	private Payment createTestPayment() throws BudgetPlannerException {
		return createTestPayment(createTestAccount(), createTestAccount());
	}

	private Payment createTestPayment(Account account) throws BudgetPlannerException {
		return createTestPayment(account, createTestAccount());
	}

	private Payment createTestPayment(Account account, Account currentAccount) throws BudgetPlannerException {
		System.out.println("account = [" + account + "], currentAccount = [" + currentAccount + "]");
		return budgetPlanner.createPayment(LocalDate.now(), 1.5f, "EUR", "test payment", account, currentAccount);
	}

	@Test
	public void test_create_account() throws Exception {
		Account createdAccount = createTestAccount();
		Account retrievedAccount = budgetPlanner.getAccountById(createdAccount.getId());
		assertEquals(createdAccount, retrievedAccount);
	}

	@Test
	public void test_create_payment() throws Exception {
		Payment createdPayment = createTestPayment();
		System.out.println("createdPayment = " + createdPayment);
		Payment retrievedPayment = budgetPlanner.getPaymentById(createdPayment.getId());
		assertEquals(createdPayment, retrievedPayment);
	}

	@Test
	public void test_get_account_by_number() throws BudgetPlannerException {
		Account createdAccount = createTestAccount();
		Account retrievedAccount = budgetPlanner.getAccountByNumber(createdAccount.getNumber());
		assertEquals(createdAccount, retrievedAccount);
	}

	@Test
	public void test_get_payments_by_account_number() throws Exception {

		Account testAccount = createTestAccount();

		List<Payment> expectedPayments = new ArrayList<>();
		expectedPayments.add(createTestPayment(testAccount)); // 1
		expectedPayments.add(createTestPayment(testAccount)); // 2
		expectedPayments.add(createTestPayment(testAccount)); // 3

		List<Payment> retrievedPayments = budgetPlanner.getPaymentsByAccountNumber (testAccount.getNumber());
		assertEquals(3, retrievedPayments.size());
		for (int i = 0; i < retrievedPayments.size(); i++) {
			assertEquals(expectedPayments.get(i), retrievedPayments.get(i));
		}

	}

	@After
	public void tearDown() throws Exception {
		budgetPlanner.rollback();
		budgetPlanner.close();
	}
}
