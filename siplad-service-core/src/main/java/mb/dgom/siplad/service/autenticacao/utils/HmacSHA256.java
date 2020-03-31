package mb.dgom.siplad.service.autenticacao.utils;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA256 implements Serializable {

	private static final long serialVersionUID = 1L;

	public static byte[] encode(byte[] signatureKey, byte[] toEncrypt) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac apikeyMac = Mac.getInstance("HmacSHA256");
		SecretKeySpec keySpec = new SecretKeySpec(signatureKey, "HmacSHA256");
		apikeyMac.init(keySpec);
		return apikeyMac.doFinal(toEncrypt);
	}
}
