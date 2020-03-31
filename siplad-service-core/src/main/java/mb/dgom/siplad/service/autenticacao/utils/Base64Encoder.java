package mb.dgom.siplad.service.autenticacao.utils;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoder implements Serializable{

	private static final long serialVersionUID = 1L;

	public static byte[] encode(byte[] b) {
        Base64 base64 = new Base64();
		return base64.encode(b);
     }
    
    public static String encode(String s, String charset) throws IOException {
    	byte[] b = s.getBytes(charset);
    	b = encode(b);
    	return new String(b, charset);
    }

    
}
