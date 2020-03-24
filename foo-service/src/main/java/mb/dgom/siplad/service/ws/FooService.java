package mb.dgom.siplad.service.ws;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/fooservice")
public class FooService {

	private static final Logger log = LoggerFactory.getLogger(FooService.class);
	
	@GetMapping(path = "/health")
	public ResponseEntity<String> obterConcursoVigentePeloIdTipo(HttpServletRequest request){
		log.info("Chegou requisicao!");
		return ResponseEntity.status(HttpStatus.OK).body("The service is alive and well");
	}
}
