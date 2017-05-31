package com.selfsoft.util.regedit;

import java.security.Key;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class RegeditForSystem {
	
	public static String doEncrypt(String key,String iv,String plainText) throws Exception{
		
		Security.addProvider(new BouncyCastleProvider());
		
		key=appendSpace(key,16);
		
		iv=appendSpace(iv,16);
		
		byte[] keyData=key.getBytes("UTF-8");
		
		Key sKey = new SecretKeySpec(keyData, "AES");   
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		
	    cipher.init(Cipher.ENCRYPT_MODE, sKey,new IvParameterSpec(iv.getBytes("UTF-8")));
	    
	    byte[] b = cipher.doFinal(plainText.getBytes());
      
	    return Base64.getEncoder().encodeToString(b);
	   
	}
	
	public static String deDecrypt(String key,String iv,String plainText) throws Exception{
		
		Security.addProvider(new BouncyCastleProvider());  
		
		key=appendSpace(key,16);
		
		iv=appendSpace(iv,16);
		
		byte[] keyData=key.getBytes("UTF-8");
		
		Key sKey = new SecretKeySpec(keyData, "AES");   
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		
	    cipher.init(Cipher.DECRYPT_MODE, sKey,new IvParameterSpec(iv.getBytes("UTF-8")));
	    
	    byte[] b = Base64.getDecoder().decode(plainText);
	    
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
			//公司名字拼音
			String name = "GXJXQCXSFWYXGS";//YDBQCXSFWYXGS//HHJDQCFWYXGS 
			//机器码
			String code = "14DAE96B64D8";//BCAEC56E785D
			//有效期
			String date = "9999-12-31";
			
			String s = doEncrypt(name,code,date);
			
			System.out.println(s);
			
			String m = deDecrypt(name,code,s);
			
			System.out.println(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
