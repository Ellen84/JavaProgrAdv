package be.pxl.student.bean;

import java.util.Objects;

public class Account {

	private int id;
	private String number;
	private String IBAN;
	private String name;

	public Account(int id, String number, String IBAN, String name) {
		this.id = id;
		this.number = number;
		this.IBAN = IBAN;
		this.name = name;
	}

	public Account(String number, String IBAN, String name) {
		this.number = number;
		this.IBAN = IBAN;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", number='" + number + '\'' +
				", IBAN='" + IBAN + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return getId() == account.getId() &&
				Objects.equals(getNumber(), account.getNumber()) &&
				Objects.equals(getIBAN(), account.getIBAN()) &&
				Objects.equals(getName(), account.getName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(), getNumber(), getIBAN(), getName());
	}
}
