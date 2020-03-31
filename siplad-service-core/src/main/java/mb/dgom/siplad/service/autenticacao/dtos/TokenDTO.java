package mb.dgom.siplad.service.autenticacao.dtos;

/**
 * Classe que representa o token e as suas informacoes
 *
 * @author Marcello Tinoco
 *
 */
public class TokenDTO {

	private String token; 
	private long userId;
	private String userName;
	private String userIdentifier;
	private long selectedProfileId;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	public long getSelectedProfileId() {
		return selectedProfileId;
	}
	public void setSelectedProfileId(long selectedProfileId) {
		this.selectedProfileId = selectedProfileId;
	}
	
}