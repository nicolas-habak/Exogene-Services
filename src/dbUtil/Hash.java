package dbUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	
	public static String hashKey(String key) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(key.getBytes());
		byte[] b = md.digest();
		
		StringBuffer sb = new StringBuffer();
		
		for (byte b1: b) {
			sb.append(Integer.toString((b1 & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
	
	public String getKey(String hash) {
		return hash;
	}
}
