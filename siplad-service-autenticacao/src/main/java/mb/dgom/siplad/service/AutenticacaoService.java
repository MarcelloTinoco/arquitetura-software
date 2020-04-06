package mb.dgom.siplad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import mb.dgom.siplad.dtos.UserTokenDTO;
import mb.dgom.siplad.dtos.UsuarioDTO;
import mb.dgom.siplad.utils.JWTTokenProvider;

@Service
public class AutenticacaoService {

	@Autowired
	private JWTTokenProvider tokenProvider;
	
	public String criarToken(UserDetails userDetails, UsuarioDTO usrDTO, Long selectedProfileId) {
		
		UserTokenDTO userToken = new UserTokenDTO();
		userToken.setUserId(usrDTO.getId());
		userToken.setUserName(userDetails.getUsername());
		userToken.setSelectedProfileId(selectedProfileId);
				
		return tokenProvider.criarToken(userDetails, userToken);
	}
}
