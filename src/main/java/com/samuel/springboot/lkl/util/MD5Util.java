package com.samuel.springboot.lkl.util;


/**
 * MD5 加密解密算法
 * User: Loki_Yung 
 * Date:2016-12-8 16:05
 */
public class MD5Util {

	/** 
	 *字符串加密 
	 *@param ssoToken 字符串 
	 *@return String 返回加密字符串 
	 */  
	public static String encrypt(String ssoToken){  
		try {  
			byte[] _ssoToken = ssoToken.getBytes("ISO-8859-1");  
			String name = new String();  
			for (int i = 0; i < _ssoToken.length; i++) {  
				int asc = _ssoToken[i];  
				_ssoToken[i] = (byte) (asc + 27);  
				name = name + (asc + 27) + "%";  
			}  
			return name;  
		}catch(Exception e){  
			e.printStackTrace() ;  
			return null;  
		}  
	}  

	/** 
	 *字符串解密 
	 *@param ssoToken 字符串 
	 *@return String 返回加密字符串 
	 */  
	public static String decrypt(String ssoToken)  
	{  
		try  
		{  
			String name = new String();  
			java.util.StringTokenizer st=new java.util.StringTokenizer(ssoToken,"%");  
			while (st.hasMoreElements()) {  
				int asc =  Integer.parseInt((String)st.nextElement()) - 27;  
				name = name + (char)asc;  
			}  

			return name;  
		}catch(Exception e)  
		{  
			e.printStackTrace() ;  
			return null;  
		}  
	}  
}
