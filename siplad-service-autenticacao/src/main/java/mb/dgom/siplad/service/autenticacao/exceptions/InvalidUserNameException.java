package mb.dgom.siplad.service.autenticacao.exceptions;

public class InvalidUserNameException extends RuntimeException{

	private static final long serialVersionUID = 2070337684799904107L;

	public InvalidUserNameException() {
		super();
	}

	public InvalidUserNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUserNameException(String message) {
		super(message);
	}

}
