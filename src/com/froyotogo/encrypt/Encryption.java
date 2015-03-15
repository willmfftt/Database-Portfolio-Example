package com.froyotogo.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encryption for the passwords, uses SHA-256 Algorithm
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class Encryption {
	
	public static String encrypt(String password, byte salt) {
		
		byte[] messageBytes = null;
		
		try {
			
			messageBytes = password.getBytes("UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			
		}
		
		MessageDigest digest = null;
		
		try {
			
			digest = MessageDigest.getInstance("SHA-256");
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		}
		
		digest.update(salt);
		byte[] encodedMessage = digest.digest(messageBytes);
		String encodedString = "";
		
		for (byte b : encodedMessage)
			encodedString += b;
		
		return encodedString;
		
	}

}
