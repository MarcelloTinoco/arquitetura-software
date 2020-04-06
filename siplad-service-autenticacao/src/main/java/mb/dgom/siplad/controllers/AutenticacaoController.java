package mb.dgom.siplad.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mb.dgom.siplad.apis.UsuarioServiceAPI;
import mb.dgom.siplad.dtos.CredenciaisUsuarioDTO;
import mb.dgom.siplad.dtos.JWTAutenticacaoDTO;
import mb.dgom.siplad.dtos.TokenDTO;
import mb.dgom.siplad.dtos.UsuarioDTO;
import mb.dgom.siplad.service.AutenticacaoService;
import mb.dgom.siplad.service.UsuarioService;
import mb.dgom.siplad.utils.Response;
import mb.dgom.siplad.vos.UsuarioVO;

//import mb.dgom.siplad.mobile.security.dto.PerfilDTO;
//import mb.dgom.siplad.mobile.security.dto.TokenDto;
//import mb.dgom.siplad.mobile.security.dto.VisibilidadeUsuarioDTO;
//import mb.dgom.siplad.mobile.security.exceptions.UserNotFoundException;
//import mb.dgom.siplad.mobile.security.helper.AutenticacaoControllerComParalelismoHelper;
//import mb.dgom.siplad.mobile.util.DateUtilSiplad;
//import mb.dgom.siplad.mobile.vos.PerfilVO;
//import mb.dgom.siplad.mobile.vos.UnidadeGestoraVO;
//import mb.dgom.siplad.mobile.vos.UsuarioPerfilUnidadeGestoraVO;
//import mb.dgom.siplad.mobile.vos.UsuarioVO;
//import mb.dgom.siplad.mobile.vos.helper.VisibilidadeUsuarioHelper;
//import mb.dgom.siplad.mobile.vos.helper.VisibilidadeUsuarioVO;
//import mb.dgom.siplad.mobile.vos.types.AtivoInativoType;
//import mb.dgom.siplad.service.autenticacao.dtos.JWTAutenticacaoDTO;
//import mb.dgom.siplad.service.autenticacao.dtos.TokenDTO;
//import mb.dgom.siplad.service.autenticacao.utils.Response;

@RestController
@RequestMapping("/v1/security/auth")
public class AutenticacaoController {

	private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	
	/**
	 * Recebe as credencias do usuário e gera o token JWE.
	 * 
	 * @param authDTO
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<TokenDTO>> login(@Valid @RequestBody CredenciaisUsuarioDTO credenciaisDTO,	BindingResult result) throws AuthenticationException {
		Response<TokenDTO> response = new Response<TokenDTO>();

		if (result.hasErrors()) {
			//log.error("Erro validando autenticacaoDTO: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Realizando autenticacao para o usuario {}.", credenciaisDTO.getUsuario());
		
		try {
			
			/**
			 * Invoca o metodo authenticate de CustomAuthenticationManager, passando umm objeto do tipo Authentication, no nosso caso a classe UsernamePasswordAuthenticationToken do SpringSecurity
			 */
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciaisDTO.getUsuario(), credenciaisDTO.getSenha()));
										
			SecurityContextHolder.getContext().setAuthentication(authentication);
				
			//Refatorar. O username necessario para a criacao do Token ja esta no 
			UserDetails userDetails = userDetailsService.loadUserByUsername(credenciaisDTO.getUsuario());
		
			
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUserName(credenciaisDTO.getUsuario());
			
			UsuarioDTO usuario = usuarioServiceAPI.getByUserName(usuarioDTO).getBody();
			
			//String tokenJWE = jweTokenUtil.obterToken(userDetails, usuario);
			String token = autenticacaoService.criarToken(userDetails, usuario, credenciaisDTO.getSelectedProfileId()); 
				
			TokenDTO tokenDTO  =  new TokenDTO(token);
			tokenDTO.setUserId(usuario.getId());
			tokenDTO.setUserName(usuario.getNome());
			tokenDTO.setSelectedProfileId(credenciaisDTO.getSelectedProfileId());
			response.setData(tokenDTO);
		
			

			
			//UsuarioVO usuario = uService.loadUserByUsername(authenticationDto.getUsuario());
			
			//if(usuario!=null && usuario.getId()!=null) {
				
			//	if(AtivoInativoType.I.equals(usuario.getSituacao())) {
					
					//Status Inativo - Informar que usuario esta bloqueado
			//		String ultimoAcesso = DateUtilSiplad.convertDateToString("dd/MM/yyyy HH:mm:ss",usuario.getUltimoAcesso());
			//		String msg = String.format("Usuário Bloqueado. Último acesso realizado em %s. Entre em contato com o Administrador do Sistema para efetuar o desbloqueio.", ultimoAcesso);
			//		response.getErrors().add(msg);
			
			//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
					
			//	}
			//	else {
			//		List<PerfilDTO> perfisDTO = null;
										
			//		List<PerfilVO> perfis = upService.obterPerfisUsuario(usuario.getId());
					
			//		if(perfis!=null && !perfis.isEmpty()) {
						
			//			perfisDTO = new ArrayList<>(perfis.size());
			//			for(PerfilVO pVO : perfis) {
			//				PerfilDTO pDTO = new PerfilDTO(pVO.getId(), pVO.getCodigo(), pVO.getNome());
			//				perfisDTO.add(pDTO);
			//			}
			//			usuario.setPerfis(perfis);
						
			//			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getUsuario());
			//			String tokenJWE = jweTokenUtil.obterToken(userDetails, usuario);
								
			//			log.info("JWE ====>>>> " + tokenJWE);		
						
			//			if(usuario.getPerfis().size()==1) {
							
			//				PerfilVO pVO = perfis.get(0);
							
			//				PerfilDTO pDTO = new PerfilDTO(pVO.getId(), pVO.getCodigo(), pVO.getNome());
							//token = jwtTokenUtil.refreshToken(token,pDTO);
							
			//				tokenJWE = jweTokenUtil.refreshToken(tokenJWE, pDTO);
							
			//				log.info("Refresed Token JWE ====>>>> " + tokenJWE);		
							
							/**
							 * Obter o usuario
							 */
			//				UsuarioVO usuarioVO = uService.getUsuarioById(usuario.getId());
			//				usuarioVO.setPerfil(pVO);
							
			//				List<UsuarioPerfilUnidadeGestoraVO> ugsPerfisUsuario = upugService.recuperarPerfilUnidadeGestoraUsuario(usuarioVO.getId(), pDTO.getId());
			//				if(ugsPerfisUsuario!=null && !ugsPerfisUsuario.isEmpty()) {
			//					usuarioVO.setUnidadesGestoras(new ArrayList<UnidadeGestoraVO>());
			//					for(UsuarioPerfilUnidadeGestoraVO u : ugsPerfisUsuario){
			//						usuarioVO.getUnidadesGestoras().add(u.getUnidadeGestora());
			//					}
			//				}
							
							/*Long startSequencial = System.currentTimeMillis();		
							//carregar a visibilidade do usuario
							VisibilidadeUsuarioHelper visibilidadeHelper = autenticacaoHelper.configureVisibilidadeUsuarioHelper(refreshedToken, usuarioVO);
							Long endSequencial = System.currentTimeMillis();
							System.out.println("Total milisegundos sequencial = " + (endSequencial - startSequencial));*/
							
			//				VisibilidadeUsuarioHelper visibilidadeHelper = null;
			//				try {
								
			//					Long startParalelo = System.currentTimeMillis();
			//					//carregar a visibilidade com paralelismo
			//					AutenticacaoControllerComParalelismoHelper autenticacaoControllerComParalelismo = 
			//							new AutenticacaoControllerComParalelismoHelper(this.planoMetaRemote, this.emRemote, this.ugRemote, tokenJWE, usuarioVO);
			//					visibilidadeHelper = autenticacaoControllerComParalelismo.configureVisibilidadeUsuarioHelper();
			//					Long endParalelo = System.currentTimeMillis();
			//					log.info(">>>> Total milisegundos paralelo = " + (endParalelo - startParalelo));
								
			//				} catch (InterruptedException | ExecutionException e) {
			//					// TODO Auto-generated catch block
			//					e.printStackTrace();
			//					response.getErrors().add("Não foi possivel obter os dados para compor a visisibilidade do usuário");
			//					return ResponseEntity.badRequest().body(response);
			//				}
							
			//				VisibilidadeUsuarioVO visibilidadeUsuarioVO = uService.carregarVisibilidadeUsuario(usuarioVO, visibilidadeHelper);
			//				VisibilidadeUsuarioDTO vuDTO = new VisibilidadeUsuarioDTO();
			//				vuDTO.setVisibilidadeUsuarioVO(visibilidadeUsuarioVO);
							
			//				String tokenVisibilidade = jweTokenUtil.generateTokenVisibilidade(tokenJWE, vuDTO);
			//				log.info(">>>> JWE Visibilidade = " + tokenVisibilidade.getBytes().length );
			//											
			//				TokenDto tokenDTO  =  new TokenDto(tokenVisibilidade);
			//				tokenDTO.setUserId(usuario.getId().toString());
			//				tokenDTO.setUserName(usuario.getNome());
			//				String acessoFormatado = usuario.getTipoAcesso() + ": " + usuario.getAcessoFormatado();
			//				String acessoFormatadoCripto = crypto.cryptToMobile(acessoFormatado);
			//				tokenDTO.setUserIdentifier(acessoFormatadoCripto);
			//				
			//				tokenDTO.setListaPerfis(null);
			//				tokenDTO.setPerfilCorrente(pDTO);
			//				response.setData(tokenDTO);
			//				
			//				response.setData(tokenDTO);
							
			//			}
			//			else {
													
			//				TokenDto tokenDTO  =  new TokenDto(tokenJWE);
			//				tokenDTO.setUserId(usuario.getId().toString());
			//				tokenDTO.setUserName(usuario.getNome());
			//				String acessoFormatado = usuario.getTipoAcesso() + ": " + usuario.getAcessoFormatado();
			//				String acessoFormatadoCripto = crypto.cryptToMobile(acessoFormatado);
			//				tokenDTO.setUserIdentifier(acessoFormatadoCripto);
			//				tokenDTO.setListaPerfis(perfisDTO);
			//				response.setData(tokenDTO);
			//			}
			//		}
			//	}
			//}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			if (ex instanceof AuthenticationException) {
				throw new BadCredentialsException("Usuario/Senha inválidos!");
			}
			if(ex instanceof UsernameNotFoundException) {
				response.getErrors().add("Usuário não encontrado.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			else {
				response.getErrors().add("Erro interno.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		}
		return ResponseEntity.ok(response);
	}
	
}
