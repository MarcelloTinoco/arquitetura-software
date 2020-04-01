package mb.dgom.siplad.service.autenticacao.configs;

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

import mb.dgom.siplad.service.autenticacao.apis.UsuarioServiceAPI;
import mb.dgom.siplad.service.autenticacao.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.service.autenticacao.dtos.UsuarioDTO;
import mb.dgom.siplad.service.autenticacao.service.UsuarioService;
import mb.dgom.siplad.service.autenticacao.vos.UsuarioVO;

@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Obetr o usuario do banco, pelo login (cpf ou nip) e a lista de perfis
		
		String value = username.replace(".", "").replace("-", "");
		
		UsuarioVO usr = usuarioService.loadUserByUserName(value);
		
		CredenciaisUsuarioDTO credenciais = new CredenciaisUsuarioDTO();
		credenciais.setUserName(username);
		
		ResponseEntity<UsuarioDTO> usuarioResponse = usuarioServiceAPI.getByUserName(credenciais);
		
		if(usr!=null && usr.getId()!=null) { 
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(DeviceRoleEnum.ROLE_WEB.name()));
			return new User(usr.getAcesso(), "", authorities);
		}	 
		else {
			throw new UsernameNotFoundException("Usuario nao encontrado: " + username);
		}
	}

}
