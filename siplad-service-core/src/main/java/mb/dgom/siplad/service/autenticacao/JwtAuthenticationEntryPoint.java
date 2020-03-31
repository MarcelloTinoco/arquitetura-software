package mb.dgom.siplad.service.autenticacao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final String USUARIO_NAO_AUTORIZADO = "Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada.";
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,	AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,	USUARIO_NAO_AUTORIZADO);
	}
	
}
