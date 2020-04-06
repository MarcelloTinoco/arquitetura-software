package mb.dgom.siplad.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.dgom.siplad.apoio.dtos.AcaoDTO;

/**
 * 
 *
 * @author Marcello Tinoco
 *
 */
@RestController
@RequestMapping("/v1/apoio/acoes")
public class AcaoController {

	private static final Logger log = LoggerFactory.getLogger(AcaoController.class);
	
	@GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcaoDTO> getById(@PathVariable("id") Long id, HttpServletRequest request) {

		ResponseEntity<AcaoDTO> responseEntity;
		
		AcaoDTO a = new AcaoDTO();
		a.setId(new Long(1));
		a.setCodigo("incluir");
		a.setNome("Incluir");
		responseEntity = ResponseEntity.ok().body(a);
		
		log.info("getById");
		
		return responseEntity;
	}

	/**
	 * obterPeloId
	 * 
	 * obterPeloCodio
	 * 
	 * gravar
	 * 
	 * atualizar
	 * 
	 * remover/{id}
	 * 
	 * obterQuantidade
	 * 
	 * buscarPaginados
	 * 
	 */
}
