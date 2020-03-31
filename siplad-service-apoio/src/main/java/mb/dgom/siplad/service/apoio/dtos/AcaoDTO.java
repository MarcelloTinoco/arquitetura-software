package mb.dgom.siplad.service.apoio.dtos;

import java.io.Serializable;

/**
 * 
 *
 * @author Marcello Tinoco
 *
 */
public class AcaoDTO implements Serializable{

	private static final long serialVersionUID = -8215526100966534295L;
	
	private Long id;
	private String codigo;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
