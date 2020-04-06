package mb.dgom.siplad.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.dgom.siplad.apoio.apis.ApoioServiceRemoteAPI;
import mb.dgom.siplad.apoio.dtos.AcaoDTO;

@RestController
@RequestMapping("/v1/testes")
public class TesteController {

	private static final Logger log = LoggerFactory.getLogger(TesteController.class);
	
	@Autowired
	private ApoioServiceRemoteAPI apoioServiceAPI;
	
	@GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcaoDTO> getById(@PathVariable("id") Long id, HttpServletRequest request) {

		log.info("Invocando api acao remota");
		
		AcaoDTO acaoRemota = apoioServiceAPI.AcaoGetById(id).getBody();
		
		log.info("Retorno api acao remota sucesso");
		
		
		return ResponseEntity.ok().body(acaoRemota);
		
		
	}
	
}



