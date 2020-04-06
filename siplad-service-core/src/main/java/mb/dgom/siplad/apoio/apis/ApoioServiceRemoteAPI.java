package mb.dgom.siplad.apoio.apis;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mb.dgom.siplad.apoio.dtos.AcaoDTO;
import mb.dgom.siplad.apoio.dtos.UsuarioDTO;

@FeignClient("sipladserviceapoio")
public interface ApoioServiceRemoteAPI {

	@PostMapping(path = "/v1/apoio/usuarios/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> UsuarioGetByUserName(@RequestBody UsuarioDTO credenciais);
	
	
	@GetMapping(path = "/v1/apoio/acoes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcaoDTO> AcaoGetById(@PathVariable("id") Long id);
	
}
