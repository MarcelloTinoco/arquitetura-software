package mb.dgom.siplad.gateway.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Classe responsavel por atuar como um filtro na WebSecurity para verificar a validade do token enviado, caso esteja presente e
 * colocar a classe UsernamePasswordAuthenticationToken no contexto de seguranca do Spring
 *
 * @author Marcello Tinoco
 *
 */
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		String token = jwtTokenProvider.resolveToken(request);
		
		if(token!=null) {
			
			String username = jwtTokenProvider.getUsernameFromToken(token);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				
				boolean isTokenValido = false;
				
				//if(request.getRequestURI().toString().contains("setPerfil")) {
				//	isTokenValido = jwtTokenProvider.tokenProvisorioValido(token);
				//}
				//else {
				//	isTokenValido = jwtTokenProvider.tokenValido(token);
				//}
				
				isTokenValido = jwtTokenProvider.tokenValido(token);
				
				if (isTokenValido) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		

		chain.doFilter(request, response);
	}

}
