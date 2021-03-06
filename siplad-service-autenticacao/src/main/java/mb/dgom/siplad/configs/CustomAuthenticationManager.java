package mb.dgom.siplad.configs;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import mb.dgom.siplad.apis.UsuarioServiceAPI;
import mb.dgom.siplad.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.dtos.UsuarioDTO;
import mb.dgom.siplad.service.UsuarioService;
import mb.dgom.siplad.vos.UsuarioVO;

/**
 * Classe responsavel por implementar AuthenticationManager e sobrescrever o metodo authenticate.
 * Este metodo recebe o objeto UsernamePasswordAuthenticationToken que foi colocado no contexto de seguranca do Spring na execucao de 
 * 
 * JWTAuthenticationTokenFilter.doFilter
 * 
 * e retorna
 *
 * @author Marcello Tinoco
 *
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager{

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationManager.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	
	//@Autowired
	//private SipladMobileCryptography crypto;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws RuntimeException {
		
		String usuario = authentication.getName();
		String senha = authentication.getCredentials().toString();
		UsernamePasswordAuthenticationToken usrToken = null;
		String senhaDecriptografada = null;
		String acessoDecriptografado = null;
		////Senha ira chegar criptografada
		//try {
		//	 acessoDecriptografado = crypto.decrypt(acesso);
		//	 senhaDecriptografada = crypto.decrypt(senha);
		//} catch (SipladMobileCryptographyInitException | SipladMobileCryptographyException e) {
		//	e.printStackTrace();
		//	LOGGER.error(e.getMessage());
		//	throw new RuntimeException(e.getMessage());
		//} 
		//if(!StringUtils.isEmpty(acessoDecriptografado) && !StringUtils.isEmpty(senhaDecriptografada)) {
		if(!StringUtils.isEmpty(usuario) && !StringUtils.isEmpty(senha)) {
			//UsuarioVO usr = usuarioService.autenticar(acessoDecriptografado, SecurityUtil.convertStringToSha512(senhaDecriptografada));
			//UsuarioVO usr = usuarioService.autenticar(usuario, senha);
			
			CredenciaisUsuarioDTO credenciais = new CredenciaisUsuarioDTO();
			credenciais.setUsuario(usuario);
			credenciais.setSenha(senha);
			ResponseEntity<UsuarioDTO> response = usuarioServiceAPI.autenticar(credenciais);
			
			UsuarioDTO usr = response.getBody();
			
			if(usr!=null && usr.getId()!=null) {
				usrToken = new UsernamePasswordAuthenticationToken(usuario, null);
			}
		}
		
		return usrToken;
	}

}
