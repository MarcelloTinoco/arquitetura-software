package mb.dgom.siplad.security.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Classe utilizada em conjunto com a classe WebSecurityConfig para tratar a nao autorizacao de acesso a um caminho ou recurso
 *
 * @author Marcello Tinoco
 *
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final String ACESSO_NEGADO = "Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada.";
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,	AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,	ACESSO_NEGADO);
	}
	
}
