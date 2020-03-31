package mb.dgom.siplad.service.autenticacao.dtos;

import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDTO {

	private static final String USUARIO_NAO_VAZIO = "Usuário não pode ser vazio.";
	private static final String SENHA_NAO_VAZIA = "Senha não pode ser vazia.";
	
	@NotEmpty(message = USUARIO_NAO_VAZIO)
	private String usuario;
	
	@NotEmpty(message = SENHA_NAO_VAZIA)
	private String senha;

	public JwtAuthenticationDTO() {
	}

	
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

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDTO [usuario=" + usuario + ", senha=XXXX ]";
	}

}
