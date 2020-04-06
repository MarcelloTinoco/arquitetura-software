package mb.dgom.siplad.dtos.filtros;

import java.io.Serializable;

/**
 * 
 *
 * @author Marcello Tinoco
 *
 */
public class AcaoFiltro implements Serializable{

	private static final long serialVersionUID = -1481441451297029381L;
	
	private String codigo;
	private String nome;
	private boolean esconderAcessar;
	
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
	public boolean isEsconderAcessar() {
		return esconderAcessar;
	}
	public void setEsconderAcessar(boolean esconderAcessar) {
		this.esconderAcessar = esconderAcessar;
	}
	
	
}
