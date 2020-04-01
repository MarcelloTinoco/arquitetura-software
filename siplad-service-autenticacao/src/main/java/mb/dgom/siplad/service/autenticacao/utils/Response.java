package mb.dgom.siplad.service.autenticacao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe reponsavel por encapsular uma resposta a uma chamada de servico
 *
 * @author Marcello Tinoco
 *
 * @param <T>
 */
public class Response<T> {

	private T data;
	private List<String> errors;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
