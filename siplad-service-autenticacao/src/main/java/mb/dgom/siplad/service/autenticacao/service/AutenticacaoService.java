package mb.dgom.siplad.service.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import mb.dgom.siplad.service.autenticacao.dtos.UserTokenDTO;
import mb.dgom.siplad.service.autenticacao.utils.JWTTokenProvider;
import mb.dgom.siplad.service.autenticacao.vos.UsuarioVO;

@Service
public class AutenticacaoService {

	@Autowired
	private JWTTokenProvider tokenProvider;
	
	public String criarToken(UserDetails userDetails, UsuarioVO usrVO, Long selectedProfileId) {
		
		UserTokenDTO userToken = new UserTokenDTO();
		userToken.setUserId(usrVO.getId());
		userToken.setUserName(userDetails.getUsername());
		userToken.setSelectedProfileId(selectedProfileId);
		
		//Refatorar
		/**
		 * TODO
		 */
				
		return tokenProvider.criarToken(userDetails, userToken);
	}
}
