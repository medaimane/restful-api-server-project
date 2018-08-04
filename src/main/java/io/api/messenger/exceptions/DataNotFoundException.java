package io.api.messenger.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3933126992782674456L;

	public DataNotFoundException(String msg) {
		super(msg);
	}
}