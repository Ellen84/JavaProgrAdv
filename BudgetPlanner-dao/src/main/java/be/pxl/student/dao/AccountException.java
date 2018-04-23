package be.pxl.student.dao;

public class AccountException extends Exception {
	public AccountException(Throwable cause) {
		super(cause);
	}

	public AccountException(String message) {
		super(message);
	}
}
