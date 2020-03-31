package mb.dgom.siplad.service.autenticacao.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mb.dgom.siplad.service.autenticacao.exceptions.SipladMobileCryptographyException;
import mb.dgom.siplad.service.autenticacao.exceptions.SipladMobileCryptographyInitException;
import mb.dgom.siplad.service.configuration.SipladConfiguration;

@Component
public class SipladMobileCryptography {

	private static final Logger LOGGER = LoggerFactory.getLogger(SipladMobileCryptography.class);
	private static final String ALGORITHM = "RSA/ECB/PKCS1Padding";
	private static final String RSA = "RSA";
	
	@Autowired
	private SipladConfiguration config;
	
	/**
	 * Local da chave privada no sistema de arquivos - EXTERNALIZAR
	 */
	//private String pathPrivateKey = "C:\\desenvolvimento\\chaves\\services\\private-pkcs8.pem";
	private String pathPrivateKey;
	/**
	 * Local da chave pública no sistema de arquivos - EXTERNALIZAR
	 */
	//private String pathPublicKey = "C:\\desenvolvimento\\chaves\\services\\public.pem";
	private String pathPublicKey;
	
	private String pathPublicKeyMobile;
	
	public static String ERRO_INICIALIZAR = "Não foi possível inicializar a classe criptografica";
	public static String ERRO_CARREGAR_CHAVE_PUBLICA = "Não foi possível carregar a chave publica";
	public static String ERRO_CARREGAR_CHAVE_PRIVADA = "Não foi possível carregar a chave privada";
	public static String ERRO_CRIPTOGRAFAR = "Não foi possível criptografar o dado";
	public static String ERRO_DECRIPTOGRAFAR = "Não foi possível decriptografar o dado";
	public static String ERRO_NAO_INICIALIZADA = "Não inicializada";
	
	private Cipher cipher;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private PublicKey mobilePublicKey;
	
	/**
	 * 	
	 * @throws SipladMobileCryptographyInitException
	 */
	public void init() throws SipladMobileCryptographyInitException{
		try {
			this.cipher = Cipher.getInstance(SipladMobileCryptography.ALGORITHM);
			
			this.pathPublicKey = config.getPathKeys() + "public.pem";
			this.pathPrivateKey = config.getPathKeys() + "private-pkcs8.pem";
			this.pathPublicKeyMobile = config.getPathPublicKey() + "public.pem";
			
			this.publicKey = this.loadPublicKey();
			this.privateKey = this.loadPrivateKey();
			this.mobilePublicKey = this.loadMobilePublicKey();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(SipladMobileCryptography.ERRO_INICIALIZAR);
			throw new SipladMobileCryptographyInitException(SipladMobileCryptography.ERRO_INICIALIZAR);
			
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SipladMobileCryptographyException
	 */
	private PublicKey loadPublicKey() throws SipladMobileCryptographyException {
		
		PublicKey pk = null;
	   
		try {
			String publicKeyPEM = FileUtils.readFileToString(new File(this.pathPublicKey), StandardCharsets.UTF_8);
			// strip of header, footer, newlines, whitespaces
		    publicKeyPEM = publicKeyPEM
		            .replace("-----BEGIN PUBLIC KEY-----", "")
		            .replace("-----END PUBLIC KEY-----", "")
		            .replaceAll("\\s", "");

		    // decode to get the binary DER representation
		    byte[] publicKeyDER = Base64.getDecoder().decode(publicKeyPEM);

		    KeyFactory keyFactory = KeyFactory.getInstance(SipladMobileCryptography.RSA);
		    pk = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyDER));
	
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			LOGGER.error(SipladMobileCryptography.ERRO_CARREGAR_CHAVE_PUBLICA);
			throw new SipladMobileCryptographyException(SipladMobileCryptography.ERRO_CARREGAR_CHAVE_PUBLICA);
		}
   	    return pk;
	}
	
	/**
	 * 
	 * @return
	 * @throws SipladMobileCryptographyException
	 */
	private PublicKey loadMobilePublicKey() throws SipladMobileCryptographyException {
		
		PublicKey pk = null;
	   
		try {
			String publicKeyPEM = FileUtils.readFileToString(new File(this.pathPublicKeyMobile), StandardCharsets.UTF_8);
			// strip of header, footer, newlines, whitespaces
		    publicKeyPEM = publicKeyPEM
		            .replace("-----BEGIN PUBLIC KEY-----", "")
		            .replace("-----END PUBLIC KEY-----", "")
		            .replaceAll("\\s", "");

		    // decode to get the binary DER representation
		    byte[] publicKeyDER = Base64.getDecoder().decode(publicKeyPEM);

		    KeyFactory keyFactory = KeyFactory.getInstance(SipladMobileCryptography.RSA);
		    pk = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyDER));
	
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			LOGGER.error(SipladMobileCryptography.ERRO_CARREGAR_CHAVE_PUBLICA);
			throw new SipladMobileCryptographyException(SipladMobileCryptography.ERRO_CARREGAR_CHAVE_PUBLICA);
		}
   	    return pk;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws SipladMobileCryptographyException
	 */
	private PrivateKey loadPrivateKey() throws SipladMobileCryptographyException {
		PrivateKey pK = null;
		try {
			String privateKeyPEM = FileUtils.readFileToString(new File(this.pathPrivateKey), StandardCharsets.UTF_8);

		    // strip of header, footer, newlines, whitespaces
		    privateKeyPEM = privateKeyPEM
		            .replace("-----BEGIN PRIVATE KEY-----", "")
		            .replace("-----END PRIVATE KEY-----", "")
		            .replaceAll("\\s", "");

		    // decode to get the binary DER representation
		    byte[] privateKeyDER = Base64.getDecoder().decode(privateKeyPEM);

		    KeyFactory keyFactory = KeyFactory.getInstance(SipladMobileCryptography.RSA);
		    pK = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyDER));
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			LOGGER.error(SipladMobileCryptography.ERRO_CARREGAR_CHAVE_PRIVADA);
			throw new SipladMobileCryptographyException(SipladMobileCryptography.ERRO_CARREGAR_CHAVE_PRIVADA);
		}
	    
	    return pK;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isInitialized() {
		boolean ok = false;
		if(this.cipher!=null && this.privateKey!=null && this.publicKey!=null && this.mobilePublicKey!=null) {
			ok = true;
		}
		return ok;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 * @throws SipladMobileCryptographyInitException
	 * @throws SipladMobileCryptographyException
	 */
	private String crypt(String value, PublicKey pk) throws SipladMobileCryptographyInitException, SipladMobileCryptographyException{
		
		String result=null;
		try {
			if(isInitialized()) {
				cipher.init(Cipher.ENCRYPT_MODE, pk);
				byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
				result =  Base64.getEncoder().encodeToString(encrypted);
			}else {
				LOGGER.error(SipladMobileCryptography.ERRO_NAO_INICIALIZADA);
				throw new SipladMobileCryptographyInitException(SipladMobileCryptography.ERRO_NAO_INICIALIZADA);
			}
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			LOGGER.error(SipladMobileCryptography.ERRO_CRIPTOGRAFAR);
			throw new SipladMobileCryptographyException(SipladMobileCryptography.ERRO_CRIPTOGRAFAR);
		}
		return result;
	}
	
	public String crypt(String value)throws SipladMobileCryptographyInitException, SipladMobileCryptographyException{
		return this.crypt(value, this.publicKey);
	}
	
	public String cryptToMobile(String value) throws SipladMobileCryptographyInitException, SipladMobileCryptographyException{
		return this.crypt(value, this.mobilePublicKey);
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 * @throws SipladMobileCryptographyInitException
	 * @throws SipladMobileCryptographyException
	 */
	public String decrypt(String value) throws SipladMobileCryptographyInitException, SipladMobileCryptographyException{
		
		String result=null;
		try {
			if(isInitialized()) {
				byte[] cifraBase64ToByte = Base64.getDecoder().decode(value);
			    cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
			    byte[] decrypted = cipher.doFinal(cifraBase64ToByte);
			    result = new String(decrypted, StandardCharsets.UTF_8);
			}
			else {
				LOGGER.error(SipladMobileCryptography.ERRO_NAO_INICIALIZADA);
				throw new SipladMobileCryptographyInitException(SipladMobileCryptography.ERRO_NAO_INICIALIZADA);
			}
			
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			LOGGER.error(SipladMobileCryptography.ERRO_CRIPTOGRAFAR);
			throw new SipladMobileCryptographyException(SipladMobileCryptography.ERRO_CRIPTOGRAFAR);
		}
		return result;
	}
}
