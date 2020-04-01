package mb.dgom.siplad.gateway.configs;

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

@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Obetr o usuario do banco, pelo login (cpf ou nip) e a lista de perfis
		
		String value = username.replace(".", "").replace("-", "");
		
		//UsuarioVO usr = usuarioService.loadUserByUserName(value);
		
		CredenciaisUsuarioDTO credenciais = new CredenciaisUsuarioDTO();
		credenciais.setUserName(username);
		
		ResponseEntity<UsuarioDTO> usuarioResponse = usuarioServiceAPI.getByUserName(credenciais);
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
