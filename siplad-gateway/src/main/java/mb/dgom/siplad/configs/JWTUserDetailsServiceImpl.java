package mb.dgom.siplad.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Classe que implementa a interface UserDetailsService, de modo a verificar se o usuario informado existe
 * ( primeiro ponto de validacao para a criacao e validacao do token, no nosso caso onde a seguranca e feita via JWT {JSON Web Tokens} )
 *
 * @author Marcello Tinoco
 *
 */
@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Obetr o usuario do banco, pelo login (cpf ou nip) e a lista de perfis
		
		String value = username.replace(".", "").replace("-", "");
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUserName(username);
		
		ResponseEntity<UsuarioDTO> usuarioResponse = usuarioServiceAPI.getByUserName(usuarioDTO);
		UsuarioDTO userDTO = usuarioResponse.getBody();
		
		if(userDTO!=null && userDTO.getId()!=null) { 
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(DeviceRoleEnum.ROLE_WEB.name()));
			return new User(userDTO.getAcesso(), "", authorities);
		}	 
		else {
			throw new UsernameNotFoundException("Usuario nao encontrado: " + username);
		}
	}

}
