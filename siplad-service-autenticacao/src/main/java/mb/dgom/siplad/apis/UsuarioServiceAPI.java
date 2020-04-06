package mb.dgom.siplad.apis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mb.dgom.siplad.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.dtos.UsuarioDTO;


@FeignClient("sipladserviceapoio")
public interface UsuarioServiceAPI {

	@PostMapping(path = "/v1/apoio/usuarios/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getByUserName(@RequestBody UsuarioDTO usuarioDTO);
	
	@PostMapping(path = "/v1/apoio/usuarios/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> autenticar(@RequestBody CredenciaisUsuarioDTO credenciais);
	
	
}
