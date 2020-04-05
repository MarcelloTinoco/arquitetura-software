package mb.dgom.siplad.gateway.configs;

import java.io.Serializable;

/**
 * Classe DTO que representa as credenciais do usuario para autenticar na arquitetura
 *
 * @author Marcello Tinoco
 *
 */
public class CredenciaisUsuarioDTO implements Serializable{

	private static final long serialVersionUID = 6265622793283202638L;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
