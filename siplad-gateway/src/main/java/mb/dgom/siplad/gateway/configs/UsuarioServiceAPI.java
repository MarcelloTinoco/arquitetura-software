package mb.dgom.siplad.gateway.configs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("sipladserviceapoio")
public interface UsuarioServiceAPI {

	@PostMapping(path = "/v1/apoio/usuarios/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getByUserName(@RequestBody CredenciaisUsuarioDTO credenciais);
	
}
