package mb.dgom.siplad.service.autenticacao.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mb.dgom.siplad.service.autenticacao.configs.SipladConfiguration;
import mb.dgom.siplad.service.autenticacao.dtos.UserTokenDTO;
import mb.dgom.siplad.service.autenticacao.vos.UsuarioVO;

@Component
public class JWTTokenProvider implements Serializable{

	private static final long serialVersionUID = -5881015954824960824L;
	
	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	
	static final String SIPLAD_MOBILE = "SIPLAD-Mob";
	static final String SIPLAD_WEB = "SIPLAD-Web";
	static final String CLAIM_TOKEN_TYPE = "typ";
	static final String CLAIM_TOKEN_TYPE_JWT = "JWT";
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "aud";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_USER_ID = "userid";
	static final String CLAIM_KEY_USER_PROFILES = "profiles";
	static final String CLAIM_KEY_SELECTED_PROFILE = "selectedprofileid";
	static final String CLAIM_KEY_USER_TOKEN = "usertoken";
	final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private SipladConfiguration config;
	
	/**
	 * 
	 * The second part of the token is the payload, which contains the claims. Claims are statements about an entity (typically, the user) and additional data
	 * 
	 * Claims são as declarações ou afirmações a respeito de uma entidade ( tipicamente o usuario ) e informações adicionais.
	 */
	
	/**
	 * Metodo responsavel por receber um objeto HttpServletRequest, aplicar as validacoes necessarias e retornar o token do cabecalho
	 * @param request
	 * @return
	 * 	O token caso esteja presente ou null
	 */
	public String resolveToken(HttpServletRequest request) {
		
		//Tenta obter o bearer token do request header especificado como Authorization
		String bearerToken = request.getHeader(AUTH_HEADER);
		//Se for diferente de nulo e a string representada por bearerToken iniciar com o valor Bearer, então, retorna a string que representa o token
		if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		
		return null;
	}
	
	/**
	 * Realiza o parse do token JWT para extrair as informações contidas no corpo dele.
	 * 
	 * @param token
	 * @return Claims
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
		
	/**
	 * Obtém o username contido no token JWT.
	 * 
	 * @param token
	 * @return String
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	public String getUsernameFromClaims(Claims claims) {
		String username;
		try {
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	

	public String getUserIdFromToken(String token) {
		String userId;
		try {
			Claims claims = getClaimsFromToken(token);
			userId = claims.get(CLAIM_KEY_USER_ID).toString();
		} catch (Exception e) {
			userId = null;
		}
		return userId;
	}
	
	public String getUserIdFromClaims(Claims claims) {
		String userId;
		try {
			userId = claims.get(CLAIM_KEY_USER_ID).toString();
		} catch (Exception e) {
			userId = null;
		}
		return userId;
	}
	
	/**
	 * Retorna a data de expiração de um token JWT.
	 * 
	 * @param token
	 * @return Date
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public Date getExpirationDateFromClaims(Claims claims) {
		Date expiration;
		try {
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	
	/**
	 * Cria um novo token (refresh).
	 * 
	 * @param token
	 * @return String
	 */
	//public String refreshToken(String token, PerfilDTO perfilSelecionado) {
	//	String refreshedToken;
	//	try {
	//		Claims claims = getClaimsFromToken(token);
	//		claims.put(CLAIM_KEY_CREATED, new Date());
	//		perfilSelecionado.setCodigo(null);
	//		PerfilDTO pToken = new PerfilDTO();
	//		pToken.setId(perfilSelecionado.getId());
	//		claims.put(CLAIM_KEY_SELECTED_PROFILE, pToken);
	//		refreshedToken = gerarToken(claims);
	//	} catch (Exception e) {
	//		refreshedToken = null;
	//	}
	//	return refreshedToken;
	//}

	/**
	 * Verifica e retorna se um token JWT é válido.
	 * 
	 * @param token
	 * @return boolean
	 */
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}

	/**
	 * Retorna um novo token JWT com base nos dados do usuários.
	 * 
	 * @param userDetails
	 * @return String
	 */
	public String criarToken(UserDetails userDetails, UserTokenDTO userTokenDTO) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USER_ID, userTokenDTO.getUserId());
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_USER_TOKEN, userTokenDTO);
		
		//List<PerfilTokenDTO> ptDTO = new ArrayList<>();
		//if(usrVO.getPerfis()!=null && !usrVO.getPerfis().isEmpty()) {
		//	usrVO.getPerfis().forEach(p -> ptDTO.add(new PerfilTokenDTO(p.getId())));
		//}
		//claims.put(CLAIM_KEY_USER_PROFILES, ptDTO);
		
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		
		claims.put(CLAIM_KEY_CREATED, new Date());
		claims.put(CLAIM_KEY_AUDIENCE,SIPLAD_WEB);
		return gerarToken(claims);
	}

	
	public Map<String, Object> setClaims(UserDetails userDetails, UsuarioVO usrVO) {
			
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USER_ID, usrVO.getId());
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		
		//List<PerfilTokenDTO> ptDTO = new ArrayList<>();
		//if(usrVO.getPerfis()!=null && !usrVO.getPerfis().isEmpty()) {
		//	if(usrVO.getPerfis().size()>1) {
		//		usrVO.getPerfis().forEach(p -> ptDTO.add(new PerfilTokenDTO(p.getId())));
		//	}
		//}
		//claims.put(CLAIM_KEY_USER_PROFILES, ptDTO);
		
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		
		claims.put(CLAIM_KEY_CREATED, new Date());
		claims.put(CLAIM_KEY_AUDIENCE,SIPLAD_MOBILE);
		//return gerarToken(claims);
		return claims;

	}
	
	
	//public UsuarioTokenDTO obterUsuarioTokenDTOFromToken(String token) {
	//	UsuarioTokenDTO usrTokenDTO = null;
	//	Claims claims = getClaimsFromToken(token);
	//	if(claims!=null && !claims.isEmpty()) {
	//		usrTokenDTO = new UsuarioTokenDTO();
	//		usrTokenDTO.setId(new Long(claims.get(CLAIM_KEY_USER_ID).toString()));
	//		
	//		PerfilDTO psDTO = mapper.convertValue(claims.get(CLAIM_KEY_SELECTED_PROFILE), PerfilDTO.class) ;
	//		usrTokenDTO.setPerfilSelecionado(psDTO);
	//		PerfilTokenDTO[] pojos = mapper.convertValue(claims.get(CLAIM_KEY_USER_PROFILES), PerfilTokenDTO[].class);
	//		List<PerfilTokenDTO> pt = Arrays.asList(pojos);
	//		usrTokenDTO.setListaPerfis(pt);
	//	}
	//	return usrTokenDTO;
	//}
	

	/**
	 * Retorna a data de expiração com base na data atual.
	 * 
	 * @return Date
	 */
	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + config.getExpiration() * 1000);
	}

	/**
	 * Verifica se um token JTW está expirado.
	 * 
	 * @param token
	 * @return boolean
	 */
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		if (dataExpiracao == null) {
			return false;
		}
		return dataExpiracao.before(new Date());
	}

	/**
	 * Gera um novo token JWT contendo os dados (claims) fornecidos.
	 * 
	 * @param claims
	 * @return String
	 */
	private String gerarToken(Map<String, Object> claims) {
		Map<String, Object> headers =  new HashMap<>();
		headers.put(CLAIM_TOKEN_TYPE, CLAIM_TOKEN_TYPE_JWT);
				
		return Jwts.builder().
				setHeader(headers).
				setClaims(claims).
				setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, config.getSecret()).compact();
	}

	
}
