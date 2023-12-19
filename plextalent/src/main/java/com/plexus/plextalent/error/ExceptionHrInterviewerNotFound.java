package com.plexus.plextalent.error;

public class ExceptionHrInterviewerNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionHrInterviewerNotFound() {
		super();
	}

	public ExceptionHrInterviewerNotFound(Long id) {
		super("No se puede encontrar al entrevistador con la ID: " + id);
	}

}
