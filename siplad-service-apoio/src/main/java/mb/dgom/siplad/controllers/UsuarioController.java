package mb.dgom.siplad.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.dgom.siplad.apoio.dtos.UsuarioDTO;
import mb.dgom.siplad.exceptions.UserNotFoundException;
import mb.dgom.siplad.security.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.services.UsuarioService;
import mb.dgom.siplad.vos.UsuarioVO;

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
	
	@PostMapping(path = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> autenticar(@RequestBody CredenciaisUsuarioDTO credenciais, HttpServletRequest request) {

		ResponseEntity<UsuarioDTO> responseEntity = null;
		
		try {
			UsuarioVO usuarioAutenticado = usuarioService.autenticar(credenciais.getUsuario(), credenciais.getSenha());
			if(usuarioAutenticado!=null) {
				UsuarioDTO user = new UsuarioDTO();
				user.setId(new Long(1));
				user.setAcesso("admin");
				user.setUserName("admin");
				user.setNome("Administrador");
				user.setStatus(true);
				
				responseEntity = ResponseEntity.ok().body(user);
			}
				
		}
		catch(UserNotFoundException unfe) {
			log.error(unfe.getMessage());
			responseEntity = ResponseEntity.badRequest().body(new UsuarioDTO());
		}
		
		return responseEntity;
	}
	
	@PostMapping(path = "/getByUserName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getByUserName(@RequestBody UsuarioDTO usuarioDTO, HttpServletRequest request) {

		ResponseEntity<UsuarioDTO> responseEntity = null;
		
		log.info("UsuarioController >>>>> Username -> " + usuarioDTO.getUserName());
		
		UsuarioDTO user = new UsuarioDTO();
		user.setId(new Long(1));
		user.setAcesso("admin");
		user.setUserName("admin");
		user.setNome("Administrador");
		user.setStatus(true);
				
		responseEntity = ResponseEntity.ok().body(user);
		
		return responseEntity;
	}
	
	
	@GetMapping( path = "/testeSeguro/{id} ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getCaminhoSeguro(@PathVariable("id") Long id, HttpServletRequest request) {

		ResponseEntity<UsuarioDTO> responseEntity = null;
		
		log.info("UsuarioController >>>>> Caminho Seguro -> " + id);
		
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
