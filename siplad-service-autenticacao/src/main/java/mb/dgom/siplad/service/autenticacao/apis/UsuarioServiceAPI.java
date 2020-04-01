package mb.dgom.siplad.service.autenticacao.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mb.dgom.siplad.service.autenticacao.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.service.autenticacao.dtos.UsuarioDTO;


@FeignClient("sipladserviceapoio")
public interface UsuarioServiceAPI {

	@PostMapping(path = "/v1/apoio/usuarios/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getByUserName(@RequestBody CredenciaisUsuarioDTO credenciais);
	
}
