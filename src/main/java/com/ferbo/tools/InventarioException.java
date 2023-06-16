package com.ferbo.tools;

public class InventarioException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6889741274171474488L;

	public InventarioException() {
		super();
	}

	/**
	 * @param message mensaje
	 */
	public InventarioException(String message) {
		super(message);
	}

	/**
	 * @param cause causa
	 */
	public InventarioException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message mensaje
	 * @param cause causa
	 */
	public InventarioException(String message, Throwable cause) {
		super(message, cause);
	}
}
