package com.plexus.plextalent.error;

public class ExceptionHrProviderCompanyNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionHrProviderCompanyNotFound() {
		super();
	}

	public ExceptionHrProviderCompanyNotFound(Long id) {
		super("No se puede encontrar la compaía con la ID: " + id);
	}
	


}
