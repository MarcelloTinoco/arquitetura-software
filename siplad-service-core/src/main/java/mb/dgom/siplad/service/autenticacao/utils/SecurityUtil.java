package mb.dgom.siplad.service.autenticacao.utils;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String ENCODE_DEFAULT = "UTF-8";
	
	public static String generatePassword(int size) {
    	String values = "0123456789abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%&*";
    	Random random = new Random();
    	StringBuilder result = new StringBuilder(size);
    	for( int i = 0; i < size; i++ ) {
    		result.append(values.charAt(random.nextInt(values.length())));
    	}
    	return result.toString();
    }
	
	/*public static String createToken(String secretKey, long timestamp) throws SecurityException {
		String signature = null;
		try {
			byte[] encode = HmacSHA256.encode(secretKey.getBytes(ENCODE_DEFAULT),(secretKey + timestamp).getBytes(ENCODE_DEFAULT));
			signature = new String(Base64Encoder.encode(encode), ENCODE_DEFAULT);
			signature = signature.replaceAll("[\r\n+]", "");
		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException ex) {
			throw new SecurityException(ex);
		}
		return signature;
	}*/
	
	public static String encryptString(String algorithm, String value) throws SecurityException {
		StringBuilder result = null;
		try {
	    	MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
	    	byte[] data = messageDigest.digest(value.getBytes(ENCODE_DEFAULT));
	    	result = new StringBuilder();
	    	for (byte b : data){
	    		result.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
	    	}
    	} catch (IOException | NoSuchAlgorithmException ex) {
    		throw new SecurityException(ex);
		}
    	return result.toString();
	}
	
	/*public static String convertStringToMd5(String value) throws SecurityException {
    	return encryptString("MD5",value);
	}*/
	
	/*public static String convertStringToSha256(String value) throws SecurityException {
    	return encryptString("SHA-256",value);
	}*/
	
	public static String convertStringToSha512(String value) throws SecurityException {
    	return encryptString("SHA-512",value);
	}
	
	/**
	 * Gera um hash utilizando o BCrypt.
	 * 
	 * @param senha
	 * @return String
	 */
	public static String gerarBCrypt(String senha) {
		if (senha == null) {
			return senha;
		}

		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}

	/**
	 * Verifica se a senha é válida.
	 * 
	 * @param senha
	 * @param senhaEncoded
	 * @return boolean
	 */
	public static boolean senhaValida(String senha, String senhaEncoded) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(senha, senhaEncoded);
	}

}
