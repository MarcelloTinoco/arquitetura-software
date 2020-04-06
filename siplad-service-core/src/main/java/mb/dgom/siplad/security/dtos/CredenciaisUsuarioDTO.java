package mb.dgom.siplad.security.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe DTO que representa as credenciais do usuario para autenticar na arquitetura.
 *
 * @author Marcello Tinoco
 *
 */
public class CredenciaisUsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Uma string que representa a chave do usuario para acesso
	 */
	private String usuario;
	
	/**
	 * Uma String que representa a senha do usuario para acesso
	 */
	private String senha;
		
	/**
	 * Um Long que identifica o perfil selecionado do usuario para acesso
	 */
	private Long selectedProfileId;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getSelectedProfileId() {
		return selectedProfileId;
	}

	public void setSelectedProfileId(Long selectedProfileId) {
		this.selectedProfileId = selectedProfileId;
	}
	
		
	
	
}
