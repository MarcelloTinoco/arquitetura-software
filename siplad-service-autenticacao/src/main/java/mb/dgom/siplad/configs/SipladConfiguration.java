package mb.dgom.siplad.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SipladConfiguration {

	@Value("${siplad.environment}")
	private String environment;

	/**
	 * JWT
	 */
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * Modulo Host
	 */
	
	/*
	 * @Value("${modulo.host.autenticacao}") private String hostAutenticacao;
	 * 
	 * @Value("${modulo.host.apoio}") private String hostApoio;
	 * 
	 * @Value("${modulo.host.execucao}") private String hostExecucao;
	 * 
	 * @Value("${modulo.host.planejamento}") private String hostPlanejamento;
	 */
	
	/**
	 * RestTemplate
	 */
	
	
	/*
	 * @Value("${resttemplate.connectionTimeout}") private Integer
	 * RestTemplateConnectionTimeout;
	 * 
	 * @Value("${resttemplate.readTimeout}") private Integer
	 * RestTemplateReadTimeout;
	 */
	
	/**
	 * JWK
	 */
	
	@Value("${jwk.kid}")
	private String jwkKid;
	
	@Value("${jwk.signing.private}")
	private String jwkSigningPrivate;
	
	@Value("${jwk.signing.public}")
	private String jwkSigningPublic;
	
	@Value("${jwk.encryption.private}")
	private String jwkEncryptionPrivate;
	
	@Value("${jwk.encryption.public}")
	private String jwkEncryptionPublic;
	
	
	/**
	 * Public/Private Key
	 * @return
	 */
	@Value("${rsa.path.keys}")
	private String pathKeys;
	
	/**
	 * Mobile public key
	 * @return
	 */
	//@Value("${rsa.path.publickey}")
	//private String pathPublicKey;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public String getJwkKid() {
		return jwkKid;
	}

	public void setJwkKid(String jwkKid) {
		this.jwkKid = jwkKid;
	}

	public String getJwkSigningPrivate() {
		return jwkSigningPrivate;
	}

	public void setJwkSigningPrivate(String jwkSigningPrivate) {
		this.jwkSigningPrivate = jwkSigningPrivate;
	}

	public String getJwkSigningPublic() {
		return jwkSigningPublic;
	}

	public void setJwkSigningPublic(String jwkSigningPublic) {
		this.jwkSigningPublic = jwkSigningPublic;
	}

	public String getJwkEncryptionPrivate() {
		return jwkEncryptionPrivate;
	}

	public void setJwkEncryptionPrivate(String jwkEncryptionPrivate) {
		this.jwkEncryptionPrivate = jwkEncryptionPrivate;
	}

	public String getJwkEncryptionPublic() {
		return jwkEncryptionPublic;
	}

	public void setJwkEncryptionPublic(String jwkEncryptionPublic) {
		this.jwkEncryptionPublic = jwkEncryptionPublic;
	}

	public String getPathKeys() {
		return pathKeys;
	}

	public void setPathKeys(String pathKeys) {
		this.pathKeys = pathKeys;
	}

	//public String getPathPublicKey() {
	//	return pathPublicKey;
	//}

	//public void setPathPublicKey(String pathPublicKey) {
	//	this.pathPublicKey = pathPublicKey;
	//}
	
	
	
}
