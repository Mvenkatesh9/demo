package com.clinivapps.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;



public class PasswordProtector {
	
	private static final char[] PASSWORD = "Awqeipoqwecklsnckjvsvssdlksdlkdwbcwefewcslkjcbw".toCharArray();
    private static final byte[] SALT = {
									        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
									        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
									    };

    public static String encrypt(String property)
    {
    	String encodedString = "";
    	try
    	{
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        encodedString = base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return encodedString;
    }

    public static String base64Encode(byte[] bytes) {
        // NB: This class is internal, and you probably should use another impl
        return new String(Base64.encodeBase64(bytes));
    }

    public static String decrypt(String property) 
    {
    	String encodedString = "";
    	try
    	{
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        encodedString = new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return encodedString;
    }

    public static byte[] base64Decode(String property) {
        return Base64.decodeBase64(property.getBytes());
    }

    
}
