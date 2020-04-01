package mb.dgom.siplad.gateway.configs;

import java.io.Serializable;

public class UserTokenDTO implements Serializable {

	private static final long serialVersionUID = 7655093792551181598L;
	
	private long userId;
	private String userName;
	private long selectedProfileId;
	
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
