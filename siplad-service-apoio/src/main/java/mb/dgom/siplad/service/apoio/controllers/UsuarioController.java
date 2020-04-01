package mb.dgom.siplad.service.apoio.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.dgom.siplad.service.apoio.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.service.apoio.dtos.UsuarioDTO;
import mb.dgom.siplad.service.apoio.services.UsuarioService;

/**
 * 
 *
 * @author Marcello Tinoco
 *
 */
@RestController
@RequestMapping("/v1/apoio/usuarios")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(AcaoController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(path = "/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getByUserName(@RequestBody CredenciaisUsuarioDTO credenciais, HttpServletRequest request) {

		ResponseEntity<UsuarioDTO> responseEntity;
		
		log.info("Username -> " + credenciais.getUserName());
		
		UsuarioDTO user = new UsuarioDTO();
		user.setId(new Long(1));
		user.setAcesso("admin");
		user.setUserName("admin");
		user.setNome("Administrador");
		user.setStatus(true);
		
		responseEntity = ResponseEntity.ok().body(user);
		
		log.info("getById");
		
		return responseEntity;
	}
	
	@PostMapping(path = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> autenticar(@RequestBody CredenciaisUsuarioDTO credenciais, HttpServletRequest request) {

		ResponseEntity<UsuarioDTO> responseEntity;
		
		UsuarioDTO user = new UsuarioDTO();
		user.setId(new Long(1));
		user.setAcesso("admin");
		user.setUserName("admin");
		user.setNome("Administrador");
		user.setStatus(true);
		
		responseEntity = ResponseEntity.ok().body(user);
		
		
		return responseEntity;
	}
	
	
}
