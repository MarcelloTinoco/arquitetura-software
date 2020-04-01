package mb.dgom.siplad.service.autenticacao.exceptions;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserNotFoundException(String arg0) {
		super(arg0);
	}
	
}
