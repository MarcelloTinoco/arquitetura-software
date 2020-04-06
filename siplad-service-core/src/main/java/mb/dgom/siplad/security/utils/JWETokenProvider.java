package mb.dgom.siplad.security.utils;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;

import mb.dgom.siplad.configs.SipladConfiguration;

import com.nimbusds.jwt.SignedJWT;

/*import mb.dgom.siplad.mobile.security.dto.PerfilDTO;
import mb.dgom.siplad.mobile.security.dto.PerfilTokenDTO;
import mb.dgom.siplad.mobile.security.dto.UsuarioTokenDTO;
import mb.dgom.siplad.mobile.security.dto.VisibilidadeUsuarioDTO;
import mb.dgom.siplad.mobile.vos.UsuarioVO;*/
import net.minidev.json.JSONObject;

@Component
public class JWETokenProvider implements Serializable{

	private static final long serialVersionUID = 2621737680520092226L;
	
	static final String TOKEN_HEADER = "Authorization";
	static final String BEARER_PREFIX = "Bearer ";
	
	static final String SIPLAD_MOBILE = "SIPLAD-Mob";
	static final String SIPLAD_SERVICE = "SIPLAD-Service";
	static final String CLAIM_TOKEN_TYPE = "typ";
	static final String CLAIM_TOKEN_TYPE_JWT = "JWT";
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "aud";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_USER_ID = "userid";
	
	//static final String CLAIM_KEY_USER_PROFILES = "profiles";
	
	static final String CLAIM_KEY_SELECTED_PROFILE = "selectedProfileId";
	
	//static final String CLAIM_KEY_VISIBILIDADE = "visibilidade";
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private SipladConfiguration config;
	
	/**
	 * metodo responsavel por gerar os claims do token ( payload do token )
	 * @param claims
	 * @return
	 */
	public  Payload generatePayload(Map<String, Object> claims) {
		
		Builder builder = new JWTClaimsSet.Builder();
		
		Iterator<Entry<String, Object>> it = claims.entrySet().iterator();
	    while (it.hasNext()) {
	      Map.Entry pair = it.next();
	      builder.claim(pair.getKey().toString(), pair.getValue());
	    }
	    JWTClaimsSet jwtClaims = builder.build();
		
	   
	    
	   Payload payload = new Payload(jwtClaims.toJSONObject());
		
		return payload;
	}
	
	private Builder generateBuilder(Map<String, Object> claims) {
		Builder builder = new JWTClaimsSet.Builder();
		
		Iterator<Entry<String, Object>> it = claims.entrySet().iterator();
	    while (it.hasNext()) {
	      Map.Entry pair = it.next();
	      builder.claim(pair.getKey().toString(), pair.getValue());
	    }
	    return builder;
	    
	}
	
		
	public RSAKey generateSenderJWK () throws JOSEException, NoSuchAlgorithmException, ParseException {

		String json = "{\r\n" + 
	    		"  \"kty\": \"RSA\",\r\n" + 
	    		"  \"d\": \"" + config.getJwkSigningPrivate() + "\",\r\n" + 
	    		"  \"e\": \"AQAB\",\r\n" + 
	    		"  \"use\": \"sig\",\r\n" + 
	    		"  \"kid\": \""  + config.getJwkKid() +  "\", " + 
	    		"  \"alg\": \"RS512\",\r\n" + 
	    		"  \"n\": \"" + config.getJwkEncryptionPublic() + "\"\r\n" + 
	    		"}"   ;
	    JSONObject key =  JSONObjectUtils.parse(json);
	    
		//RSAKey senderJWK = new RSAKeyGenerator(2048). keyID("123").keyUse(KeyUse.SIGNATURE).generate();
		
		RSAKey senderJWK = RSAKey.parse(key);
		//RSAKey senderPublicJWK = senderJWK.toPublicJWK();
		return senderJWK;
	}
	
	public RSAKey generateRecipientJWK () throws JOSEException, ParseException {
		      
		 String json = "{\r\n" + 
		 		"			  \"kty\": \"RSA\",\r\n" + 
		 		"			  \"d\": \"" + config.getJwkEncryptionPrivate() + "\",\r\n" + 
		 		"			  \"e\": \"AQAB\",\r\n" + 
		 		"			  \"use\": \"enc\",\r\n" + 
		 		"			  \"kid\": \"" + config.getJwkKid() +  "\",\r\n" + 
		 		"			  \"alg\": \"RS512\",\r\n" + 
		 		"			  \"n\": \"" + config.getJwkEncryptionPublic()  + "\"\r\n" + 
		 		"			}"   ;
		    
		 JSONObject key =  JSONObjectUtils.parse(json);
		    
		//RSAKey senderJWK = new RSAKeyGenerator(2048). keyID("123").keyUse(KeyUse.SIGNATURE).generate();
			
		RSAKey recipientJWK = RSAKey.parse(key);
		//RSAKey senderPublicJWK = senderJWK.toPublicJWK();
		return recipientJWK;
			
		
		
/*		RSAKey recipientJWK = new RSAKeyGenerator(2048).keyID("456"). keyUse(KeyUse.ENCRYPTION).generate();
		
		RSAKey recipientPublicJWK = recipientJWK.toPublicJWK();
		return recipientPublicJWK;*/
		
	}
	
	public Map<String, Object> setClaims(UserDetails userDetails, long userId) {
			
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(CLAIM_KEY_USER_ID, userId);
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		
		claims.put(CLAIM_KEY_CREATED, new Date());
		claims.put(CLAIM_KEY_AUDIENCE,SIPLAD_MOBILE);
		//return gerarToken(claims);
		return claims;

	}
	
	/**
	 * Retorna a data de expiração com base na data atual.
	 * 
	 * @return Date
	 */
	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + config.getExpiration() * 1000);
	}
	
	/**
	 * Metodo responsavel por gerar o token JWE
	 * @param claims
	 * @return
	 * @throws JOSEException
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 */
	private String gerarToken(Map<String, Object> claims) throws JOSEException , NoSuchAlgorithmException, ParseException{
		//Cria um JWT assinado 
		SignedJWT signedJWT = new SignedJWT(
				  new JWSHeader.Builder(JWSAlgorithm.RS512).keyID(generateSenderJWK().getKeyID()).build(), //JWSHeader
		          generateBuilder(claims).expirationTime(gerarDataExpiracao()).build() //JWT ClaimsSet
		          );
		
		// Sign the JWT
		signedJWT.sign(new RSASSASigner(generateSenderJWK()));

		// Create JWE object with signed JWT as payload
		JWEObject jweObject = new JWEObject(
		    new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
		        .contentType(CLAIM_TOKEN_TYPE_JWT) // required to indicate nested JWT
		        .build(),
		    new Payload(signedJWT));

		// Encrypt with the recipient's public key
		jweObject.encrypt(new RSAEncrypter(generateRecipientJWK().toPublicJWK()));

		// Serialise to JWE compact form
		String jweString = jweObject.serialize();
		
		return jweString;
	}
	
	public String obterToken(UserDetails userDetails, long userId ) throws JOSEException , NoSuchAlgorithmException, ParseException{
		Map<String, Object> claims = setClaims(userDetails, userId);
		return gerarToken(claims);
	}
	
	
	/**
	 * Cria um novo token (refresh).
	 * 
	 * @param token
	 * @return String
	 */
	public String refreshToken(String token, long selectedProfileId) {
		String refreshedToken;
		try {
			
			JWTClaimsSet jwtClaims = getClaimsFromToken(token);
			Map<String,Object> claimsMap = jwtClaims.getClaims();
			
			Map<String,Object> modifiedMap = new HashMap<String, Object>();
			modifiedMap.putAll(claimsMap);
			
			modifiedMap.put(CLAIM_KEY_CREATED, new Date());
			modifiedMap.put(CLAIM_KEY_SELECTED_PROFILE, selectedProfileId);
			
			refreshedToken = gerarToken(modifiedMap);
			
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	public String refreshTokenFromClaims(JWTClaimsSet jwtClaims, long selectedProfileId) {
		String refreshedToken;
		try {
			
			//JWTClaimsSet jwtClaims = getClaimsFromToken(token);
			Map<String,Object> claimsMap = jwtClaims.getClaims();
			
			Map<String,Object> modifiedMap = new HashMap<String, Object>();
			modifiedMap.putAll(claimsMap);
			
			modifiedMap.put(CLAIM_KEY_CREATED, new Date());
			modifiedMap.put(CLAIM_KEY_SELECTED_PROFILE, selectedProfileId);
			
			refreshedToken = gerarToken(modifiedMap);
			
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	
	
	/*
	 * @Deprecated
	 * public String generateTokenVisibilidade(String token, VisibilidadeUsuarioDTO
	 * visibilidade) { String tokenVisibilidade = null; try {
	 * 
	 * JWTClaimsSet jwtClaims = getClaimsFromToken(token); Map<String,Object>
	 * claimsMap = jwtClaims.getClaims();
	 * 
	 * Map<String,Object> modifiedMap = new HashMap<String, Object>();
	 * modifiedMap.putAll(claimsMap);
	 * 
	 * modifiedMap.put(CLAIM_KEY_CREATED, new Date());
	 * 
	 * modifiedMap.put(CLAIM_KEY_VISIBILIDADE, visibilidade);
	 * 
	 * tokenVisibilidade = gerarToken(modifiedMap);
	 * 
	 * } catch (Exception e) { tokenVisibilidade = null; }
	 * 
	 * return tokenVisibilidade;
	 * 
	 * }
	 */
	
	/*public String createToken(UserDetails userDetails, UsuarioVO usrVO) {
		String token = "";
		
		//passhphrase - _SiPL@DS3cREtMOb1LE_
		*//**
		 * 	salt=A863A04C9011F83E
			key=2586391187755D1A58FBD26CB754283C
			iv =82696C21BC05DE3D9B9F2BA01B7C7A32
		 *//*
		//String secret = "841D8A6C80CBA4FCAD32D5367C18C53B";
		String secret = "2586391187755D1A58FBD26CB754283C";
		byte[] secretKey = secret.getBytes();

		try {
			
			Map<String, Object> claims = setClaims(userDetails, usrVO);
			
			DirectEncrypter encrypter = new DirectEncrypter(secretKey);
			
			//JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
			JWTClaimsSet jwtClaims = generateBuilder(claims).build();
			
			
			JWSSigner signer = new MACSigner(secret);
			
			// Prepare JWS object with "Hello, world!" payload
			JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512), new Payload(jwtClaims.toJSONObject()));

			// Apply the HMAC
			jwsObject.sign(signer);

			// To serialize to compact form, produces something like
			// eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
			token = jwsObject.serialize();

			
			JWEObject jweObject = new JWEObject(generateHeader(), new Payload(jwtClaims.toJSONObject()));
			
			jweObject.encrypt(encrypter);

			token = jweObject.serialize();
			
		
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		
		return token;
	}*/
	
	/**
	 * Verifica se existe o id do usuario no token e não está expirado
	 * @param token
	 * @return
	 */
	public boolean tokenProvisorioValido(String token) {
		String idUsuario = this.getUserIdFromToken(token);
		return !tokenExpirado(token) && !StringUtils.isEmpty(idUsuario);
	}
	
	/**
	 * Verifica se existe o id do usuario no token, perfil selecionado e não está expirado
	 * 
	 * @param token
	 * @return boolean
	 */
	public boolean tokenValido(String token) {
		String idUsuario = this.getUserIdFromToken(token);
		boolean hasProfileOnToken = this.hasProfileSettedOnToken(token);
		return !tokenExpirado(token) && !StringUtils.isEmpty(idUsuario) && hasProfileOnToken;
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
	
	public JWTClaimsSet getClaimsFromToken(String jweToken) throws JOSEException, ParseException {
		// Parse the JWE string
		JWEObject jweObject = JWEObject.parse(jweToken);

		// Decrypt with private key
		jweObject.decrypt(new RSADecrypter(generateRecipientJWK()));

		// Extract payload
		SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

		return signedJWT.getJWTClaimsSet();
		
	}
	
	
	/**
	 * Obtém o username (email) contido no token JWT.
	 * 
	 * @param token
	 * @return String
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public String getUserIdFromToken(String token) {
		String userId;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			userId = claims.getLongClaim(CLAIM_KEY_USER_ID).toString();
		} catch (Exception e) {
			userId = null;
		}
		return userId;
	}
	
	public String getUserIdFromClaims(JWTClaimsSet claims) {
		String userId;
		try {
			userId = claims.getLongClaim(CLAIM_KEY_USER_ID).toString();
		} catch (Exception e) {
			userId = null;
		}
		return userId;
	}
	
	public boolean hasProfileSettedOnToken(String token) {
		boolean hasProfile = false;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			if(claims!=null) {
				Map<String, Object> mapClaims = claims.getClaims();
				if(mapClaims!=null && !mapClaims.isEmpty()) {
					Long profileSetted = (Long)mapClaims.get(CLAIM_KEY_SELECTED_PROFILE);
					if(profileSetted!=null) {
						hasProfile = true;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasProfile;
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
			JWTClaimsSet claims = getClaimsFromToken(token);
			expiration = claims.getExpirationTime();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	/*
	 * public UsuarioTokenDTO obterUsuarioTokenDTOFromToken(String token) throws
	 * JOSEException, ParseException { UsuarioTokenDTO usrTokenDTO = null;
	 * JWTClaimsSet claims = getClaimsFromToken(token); if(claims!=null) {
	 * Map<String, Object> mapClaims = claims.getClaims(); if(mapClaims!=null &&
	 * !mapClaims.isEmpty()) { usrTokenDTO = new UsuarioTokenDTO();
	 * usrTokenDTO.setId(new Long(mapClaims.get(CLAIM_KEY_USER_ID).toString()));
	 * 
	 * PerfilDTO psDTO =
	 * mapper.convertValue(mapClaims.get(CLAIM_KEY_SELECTED_PROFILE),
	 * PerfilDTO.class) ; usrTokenDTO.setPerfilSelecionado(psDTO); PerfilTokenDTO[]
	 * pojos = mapper.convertValue(mapClaims.get(CLAIM_KEY_USER_PROFILES),
	 * PerfilTokenDTO[].class); List<PerfilTokenDTO> pt = Arrays.asList(pojos);
	 * usrTokenDTO.setListaPerfis(pt); } }
	 * 
	 * 
	 * return usrTokenDTO; }
	 * 
	 * public UsuarioTokenDTO obterUsuarioTokenDTOFromClaims(JWTClaimsSet claims)
	 * throws JOSEException, ParseException { UsuarioTokenDTO usrTokenDTO = null;
	 * if(claims!=null) { Map<String, Object> mapClaims = claims.getClaims();
	 * if(mapClaims!=null && !mapClaims.isEmpty()) { usrTokenDTO = new
	 * UsuarioTokenDTO(); usrTokenDTO.setId(new
	 * Long(mapClaims.get(CLAIM_KEY_USER_ID).toString()));
	 * 
	 * PerfilDTO psDTO =
	 * mapper.convertValue(mapClaims.get(CLAIM_KEY_SELECTED_PROFILE),
	 * PerfilDTO.class) ; usrTokenDTO.setPerfilSelecionado(psDTO); PerfilTokenDTO[]
	 * pojos = mapper.convertValue(mapClaims.get(CLAIM_KEY_USER_PROFILES),
	 * PerfilTokenDTO[].class); List<PerfilTokenDTO> pt = Arrays.asList(pojos);
	 * usrTokenDTO.setListaPerfis(pt); } }
	 * 
	 * 
	 * return usrTokenDTO; }
	 */
	
	/**
	 * Metodo responsavel por extrair a visibilidade do usuario do token
	 * @param token
	 * @return
	 * @throws JOSEException
	 * @throws ParseException
	 */
	/*
	 * public VisibilidadeUsuarioDTO getVisibilidadeUsuarioTokenDTOFromToken(String
	 * token) throws JOSEException, ParseException { VisibilidadeUsuarioDTO
	 * visibilidadeUsuarioDTO = null; JWTClaimsSet claims =
	 * getClaimsFromToken(token); if(claims!=null) { Map<String, Object> mapClaims =
	 * claims.getClaims(); if(mapClaims!=null && !mapClaims.isEmpty()) {
	 * visibilidadeUsuarioDTO =
	 * mapper.convertValue(mapClaims.get(CLAIM_KEY_VISIBILIDADE),
	 * VisibilidadeUsuarioDTO.class) ; } } return visibilidadeUsuarioDTO; }
	 */
	
	/**
	 * Metodo responsavel por extrair a visibilidade do usuario do token
	 * @param token
	 * @return
	 * @throws JOSEException
	 * @throws ParseException
	 */
	/*
	 * public VisibilidadeUsuarioDTO
	 * getVisibilidadeUsuarioTokenDTOFromClaims(JWTClaimsSet claims) throws
	 * JOSEException, ParseException { VisibilidadeUsuarioDTO visibilidadeUsuarioDTO
	 * = null; if(claims!=null) { Map<String, Object> mapClaims =
	 * claims.getClaims(); if(mapClaims!=null && !mapClaims.isEmpty()) {
	 * visibilidadeUsuarioDTO =
	 * mapper.convertValue(mapClaims.get(CLAIM_KEY_VISIBILIDADE),
	 * VisibilidadeUsuarioDTO.class) ; } } return visibilidadeUsuarioDTO; }
	 */
	
}
