package mb.dgom.siplad.service.autenticacao.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class JWTAutenticacaoDTO {

	@NotEmpty(message = "Usuário não pode ser vazio.")
	private String usuario;
	
	@NotEmpty(message = "Senha não pode ser vazia.")
	private String senha;
	
	@NotNull(message = "Id do perfil selecionado nao pode ser vazio.")
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

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [usuario=" + usuario + ", senha=XXXX ]";
	}
}
