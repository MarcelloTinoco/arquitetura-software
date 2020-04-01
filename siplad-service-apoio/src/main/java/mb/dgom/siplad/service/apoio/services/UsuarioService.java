package mb.dgom.siplad.service.apoio.services;

import org.springframework.stereotype.Service;

import mb.dgom.siplad.service.apoio.exceptions.InvalidUserNameException;
import mb.dgom.siplad.service.apoio.exceptions.UserNotFoundException;
import mb.dgom.siplad.service.apoio.vos.UsuarioVO;

@Service
public class UsuarioService {

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws UserNotFoundException
	 * @throws InvalidUserNameException
	 */
	public UsuarioVO getByUserName(String userName) throws UserNotFoundException, InvalidUserNameException{
		
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
				throw new UserNotFoundException("Usuario nao econtrado");
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
