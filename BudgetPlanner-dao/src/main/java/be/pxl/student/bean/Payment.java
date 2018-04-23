package be.pxl.student.bean;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private LocalDate date;
	private float amount;
	private String currency = "EUR";
	private String detail;
	private int accountId;
	private int counterAccountId;
	private int labelId;

	@ManyToOne
	@JoinColumn(name = "LabelId")
	private Label LabelId;

	public Payment(int id, LocalDate date, float amount, String currency, String detail, int accountId, int counterAccountId, int labelId) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.currency = currency;
		this.detail = detail;
		this.accountId = accountId;
		this.counterAccountId = counterAccountId;
		this.labelId = labelId;
	}

	public Payment(LocalDate date, float amount, String currency, String detail, int accountId, int counterAccountId, int labelId) {
		this.date = date;
		this.amount = amount;
		this.currency = currency;
		this.detail = detail;
		this.accountId = accountId;
		this.counterAccountId = counterAccountId;
		this.labelId = labelId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCounterAccountId() {
		return counterAccountId;
	}

	public void setCounterAccountId(int counterAccountId) {
		this.counterAccountId = counterAccountId;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"id=" + id +
				", date=" + date +
				", amount=" + amount +
				", currency='" + currency + '\'' +
				", detail='" + detail + '\'' +
				", accountId=" + accountId +
				", counterAccountId=" + counterAccountId +
				", labelId=" + labelId +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Payment payment = (Payment) o;
		return getId() == payment.getId() &&
				Float.compare(payment.getAmount(), getAmount()) == 0 &&
				getAccountId() == payment.getAccountId() &&
				getCounterAccountId() == payment.getCounterAccountId() &&
				getLabelId() == payment.getLabelId() &&
				Objects.equals(getDate(), payment.getDate()) &&
				Objects.equals(getCurrency(), payment.getCurrency()) &&
				Objects.equals(getDetail(), payment.getDetail());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(), getDate(), getAmount(), getCurrency(), getDetail(), getAccountId(), getCounterAccountId(), getLabelId());
	}
}
