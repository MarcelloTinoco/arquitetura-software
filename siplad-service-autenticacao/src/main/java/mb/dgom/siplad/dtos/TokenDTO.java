package mb.dgom.siplad.dtos;

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
	private long selectedProfileId;
	
	public TokenDTO(String token) {
		this.token = token;
	}
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
	public long getSelectedProfileId() {
		return selectedProfileId;
	}
	public void setSelectedProfileId(long selectedProfileId) {
		this.selectedProfileId = selectedProfileId;
	}
	
}
