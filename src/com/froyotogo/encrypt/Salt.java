package com.froyotogo.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Salt for the encryption
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class Salt {

	public static byte generateSalt() {
		
		SecureRandom randomGen = null;
		
		try {
			
			randomGen = SecureRandom.getInstance("SHA1PRNG");
			
		} catch (NoSuchAlgorithmException e) {
			
			System.err.println("Error Generating Salt: " + e.toString());
			
		}
		
		byte[] salt = new byte[1];
		randomGen.nextBytes(salt);
		
		return salt[0];
		
	}
	
}
