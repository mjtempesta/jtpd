package org.jtpd.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.log4j.Logger;

public final class EncryptionService {

	private static EncryptionService instance = new EncryptionService();

	private Logger logger = Logger.getLogger("appLogger");
	
	public  EncryptionService() {
	}

	public static EncryptionService getInstance() {
		return new EncryptionService() ;
	}
	
	public String encrypt(String str) {
		
		Cipher ecipher = null ;
		Cipher dcipher = null ;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt - 1 " );
			}
			
			char[] password = "1234jAva43219876".toCharArray();
		    
			byte[] salt = {
			        (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
			        (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
			    };
			
		    int iterationCount = 20;
		    PBEKeySpec pbeSpec = new PBEKeySpec(password);
		    SecretKeyFactory keyFact = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		    	
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt - 2 " );
			}
		    ecipher = Cipher.getInstance("PBEWithMD5AndDES");
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt ecipher done - 3 " );
			}
		    dcipher = Cipher.getInstance("PBEWithMD5AndDES");
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt dcipher done - 4 " );
			}
		    
		    Key sKey = keyFact.generateSecret(pbeSpec);
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt sKey done - 5 " );
			}
		    

		    ecipher.init(Cipher.ENCRYPT_MODE, sKey, new PBEParameterSpec(salt, iterationCount));
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt  ecipher.init done - 6 " );
			}
		    
		    dcipher.init(Cipher.DECRYPT_MODE, sKey, new PBEParameterSpec(salt, iterationCount));
			
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  encrypt dcipher.init done - 7 " );
			}
			
		    // Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes();
		
			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			if (logger.isDebugEnabled()) {
    			logger.debug(" EncryptionService  encrypt - 8  " );
    		}
			// Encode bytes to base64 to get a string
			// TODO IMPORTANT : PASS DECODE : Baska bir paket kullanalým
			String newpassword = ""; // new sun.misc.BASE64Encoder().encode(enc);
			
			if (logger.isDebugEnabled()) {
    			logger.debug(" EncryptionService  encrypt - 9  " );
    		}
			return newpassword ; 
		} catch (Exception e) {
			logger.error(" " + e);
			return null;
		} 
	}

	public String decrypt(String str) {
		
		Cipher ecipher = null ;
		Cipher dcipher = null ;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt - 1 " );
			}
			
			char[] password = "1234jAva43219876".toCharArray();
		    
			byte[] salt = {
			        (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
			        (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
			    };
			
		    int iterationCount = 20;
		    PBEKeySpec pbeSpec = new PBEKeySpec(password);
		    SecretKeyFactory keyFact = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		    	
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt - 2 " );
			}
		    ecipher = Cipher.getInstance("PBEWithMD5AndDES");
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt ecipher done - 3 " );
			}
		    dcipher = Cipher.getInstance("PBEWithMD5AndDES");
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt dcipher done - 4 " );
			}
		    
		    Key sKey = keyFact.generateSecret(pbeSpec);
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt sKey done - 5 " );
			}
		    

		    ecipher.init(Cipher.ENCRYPT_MODE, sKey, new PBEParameterSpec(salt, iterationCount));
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt  ecipher.init done - 6 " );
			}
		    
		    dcipher.init(Cipher.DECRYPT_MODE, sKey, new PBEParameterSpec(salt, iterationCount));
			
		    if (logger.isDebugEnabled()) {
				logger.debug(" EncryptionService  decrypt dcipher.init done - 7 " );
			}
			// Decode base64 to get bytes
			// TODO IMPORTANT : PASS DECODE : Baska bir paket kullanalým
		    //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
		    byte[] dec = null;
			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8);
			
		} catch (Exception e) {
			logger.error(" " + e);
			return null;
		} 
		
	}

    public String generatePassword() {
    	
    	if (logger.isDebugEnabled()) {
			logger.debug(" EncryptionService  generatePassword started - 1  " );
		}
    	int one = (int )(Math.random() * 1000) ;
    	int two = (int )(Math.random() * 1000) ;
    	int three = (int )(Math.random() * 1000) ;
    	int four = (int )(Math.random() * 1000) ;
    	
    	String prePassword = " " + one+ " " + two + " "+ three+" " + four ; 
    	String password =  this.encrypt(prePassword) ;
    	if (logger.isDebugEnabled()) {
			logger.debug(" EncryptionService  generatePassword  - 2  " + password );
		}
    	if (password!= null && password.length() > 7) {
    		return password.substring(0,7);
    	} else  if (password != null && password.length() < 7){
    		if (logger.isDebugEnabled()) {
    			logger.debug(" EncryptionService  generatePassword done  " + password );
    		}
    		return password ;
    	} else {
    		return "jUolP09";
    	}
    	
    	
    	
    }
	
	public static void main(String[] args) {
		EncryptionService es = EncryptionService.getInstance();
		String one = "altug@gmail.com" ;
		one = es.encrypt(one) ; 
		System.out.println(" encrypt " +  one );
		
		one = es.decrypt("xsnXIm0+5lQ=") ;
		System.out.println(" decrypt " +  one );
		
		System.out.println(" ------------------- " ); 
		
		String pass1 = es.generatePassword();
		System.out.println("   " + pass1 ); 
		
		String pass2 = es.generatePassword();
		System.out.println("   " + pass2 );
		
		String pass3 = es.generatePassword();
		System.out.println("   " + pass3 );
	}
}
