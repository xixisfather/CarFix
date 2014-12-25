package com.selfsoft.util.regedit;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.pinyin.PinyinHelper;

import sun.misc.BASE64Decoder;

public class RegeditForSystem {
	
	public static String doEncrypt(String key,String iv,String plainText) throws Exception{
		
		//Security.addProvider(new BouncyCastleProvider());
		
		key=appendSpace(key,16);
		
		iv=appendSpace(iv,16);
		
		byte[] keyData=key.getBytes("UTF-8");
		
		Key sKey = new SecretKeySpec(keyData, "AES");   
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		
	    cipher.init(Cipher.ENCRYPT_MODE, sKey,new IvParameterSpec(iv.getBytes("UTF-8")));
	    
	    byte[] b = cipher.doFinal(plainText.getBytes());
      
	    return new sun.misc.BASE64Encoder().encode(b);
	   
	}
	
	public static String deDecrypt(String key,String iv,String plainText) throws Exception{
		
		Security.addProvider(new BouncyCastleProvider());  
		
		key=appendSpace(key,16);
		
		iv=appendSpace(iv,16);
		
		byte[] keyData=key.getBytes("UTF-8");
		
		Key sKey = new SecretKeySpec(keyData, "AES");   
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		
	    cipher.init(Cipher.DECRYPT_MODE, sKey,new IvParameterSpec(iv.getBytes("UTF-8")));
	    
	    byte[] b = new BASE64Decoder().decodeBuffer(plainText);
	    
	    byte[] c = cipher.doFinal(b);
	    
	    return new String(c);
		
	}
	
	
	private static String appendSpace(String str,int length)
	{
		StringBuffer sb=new StringBuffer();
		
		if(null!=str){
			
			if(str.length()<length)
			{
				for(int i=0;i<length-str.length();i++)
				{
					sb.append(" ");
				}
				str+=sb.toString();
			}
			else{
				
				str = str.substring(0,16);
			}
			
		}
		
		return str;
	}
	
	public static void main(String[] args)
	{
		try {
			
			String s = doEncrypt("KSDZ","","2011-09-29");
			
			System.out.println(s);
			
			String m = deDecrypt("NNSDZQCWXFWYXGS","207C8F18B216","tHg4R3G+Sp9wk6Ha6SnQ2w==");
			
			System.out.println(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
