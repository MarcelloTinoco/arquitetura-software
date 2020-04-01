package mb.dgom.siplad.service.autenticacao.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mb.dgom.siplad.service.autenticacao.exceptions.InvalidUserNameException;
import mb.dgom.siplad.service.autenticacao.exceptions.UserNotFoundException;
import mb.dgom.siplad.service.autenticacao.vos.UsuarioVO;

@Service
public class UsuarioService {

	/**
	 * Metodo utilizado para carregar o usuario pelo seu userName
	 * @param userName
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws InvalidUserNameException
	 */
	public UsuarioVO loadUserByUserName(String userName) throws UsernameNotFoundException, InvalidUserNameException{
		
		UsuarioVO user = null;
		if(userName!=null && !"".equals(userName.trim())){
			if("admin".equals(userName)) {
				user = new UsuarioVO();
				user.setId(new Long(1));
				user.setAcesso("admin");
				user.setUserName("admin");
				user.setNome("Administrador");
				user.setStatus(true);
			}
			else {
				throw new UsernameNotFoundException("Usuario nao econtrado");
			}
		}
		else {
			throw new InvalidUserNameException("Usuario nao econtrado");
		}
		
		return user;
		
	}
	
	public UsuarioVO autenticar(String usuario, String senha) throws UserNotFoundException{
		UsuarioVO user = null;
		if("admin".equals(usuario) && "123456".equals(senha)) {
			user = new UsuarioVO();
			user.setId(new Long(1));
			user.setUserName("admin");
			user.setNome("Administrador");
			user.setStatus(true);
		}
		else {
			throw new UserNotFoundException("Usuario nao econtrado");
		}
		return user;
	}
}
