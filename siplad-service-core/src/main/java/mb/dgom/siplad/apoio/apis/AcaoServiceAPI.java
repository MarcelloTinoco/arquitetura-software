package mb.dgom.siplad.apoio.apis;

import javax.websocket.server.PathParam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import mb.dgom.siplad.apoio.dtos.AcaoDTO;

//@FeignClient("sipladserviceapoio")
public interface AcaoServiceAPI {
	
	@GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcaoDTO> getById(@PathParam("id") Long id);
	
}
