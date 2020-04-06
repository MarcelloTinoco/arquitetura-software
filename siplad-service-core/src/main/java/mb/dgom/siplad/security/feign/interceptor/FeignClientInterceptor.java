package mb.dgom.siplad.security.feign.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import mb.dgom.siplad.security.web.ThreadLocalJWTTokenUtil;

/**
 * Classe responsavel por interceptar as chamadas via feignClient e embutir o token JWT caso ele exista no cabecalho
 *
 * @author Marcello Tinoco
 *
 */

@Component
public class FeignClientInterceptor implements RequestInterceptor {

	  private static final String AUTHORIZATION_HEADER="Authorization";
	  private static final String TOKEN_TYPE = "Bearer";

	  @Autowired
	  private ThreadLocalJWTTokenUtil threadLocal;
	  
	  @Override
	  public void apply(RequestTemplate requestTemplate) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated() && authentication.getDetails() instanceof WebAuthenticationDetails) {
	    	requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, threadLocal.getToken()));
	    }
	  }
}