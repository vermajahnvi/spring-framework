package spring_hibernate.controller;

public class UserNotFoundByIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
    public UserNotFoundByIdException(String message) {
        this.message = message;
    }

	
	

}
