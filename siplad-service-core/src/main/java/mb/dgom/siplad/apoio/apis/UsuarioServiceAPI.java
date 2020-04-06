package mb.dgom.siplad.apoio.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mb.dgom.siplad.apoio.dtos.UsuarioDTO;

/**
 * Interface responsavel por abstrair a chamada remota a outro servico.
 * Ela esta apoiada sobre o Service Discovery para saber onde o servico se encontra, sem a necessidade de saber seu endereco fisico
 *
 * @author Marcello Tinoco
 *
 */

//@FeignClient("sipladserviceapoio")
public interface UsuarioServiceAPI {

	@PostMapping(path = "/v1/apoio/usuarios/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getByUserName(@RequestBody UsuarioDTO credenciais);
	
}
