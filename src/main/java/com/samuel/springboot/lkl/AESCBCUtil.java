package com.samuel.springboot.lkl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * @author: xuzongxin
 * @date: 2018/8/21 20:11
 * @description:
 */
public class AESCBCUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AESCBCUtil.class);
    private static final String ENCODING = "GBK";

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**
     * 填充向量
     */
    private static final String FILL_VECTOR = "1234560405060708";

    /**
     * 加密字符串
     *
     * @param content  字符串
     * @param password 密钥KEY
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String password) {
//        if (StringUtils.isAnyEmpty(content, password)) {
//            LOGGER.error("AES encryption params is null");
//            return null;
//        }

        byte[] raw = hex2byte(password);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(FILL_VECTOR.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] anslBytes = content.getBytes(ENCODING);
            byte[] encrypted = cipher.doFinal(anslBytes);
            return byte2hex(encrypted).toUpperCase();
        } catch (Exception e) {
            LOGGER.error("AES encryption operation has exception,content:{},password:{}", content, password, e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  解密前的字符串
     * @param password 解密KEY
     * @return
     * @throws Exception
     * @author cdduqiang
     * @date 2014年4月3日
     */
    public static String decrypt(String content, String password) {
//        if (StringUtils.isAnyEmpty(content, password)) {
//            LOGGER.error("AES decryption params is null");
//            return null;
//        }

        try {
            byte[] raw = hex2byte(password);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(FILL_VECTOR.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = hex2byte(content);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, ENCODING);
        } catch (Exception e) {
            LOGGER.error("AES decryption operation has exception,content:{},password:{}", content, password, e);
        }
        return null;
    }

    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) throws Exception {
        String str = "Hello world";
        //必须为16位
        String key = "bUYJ3nTV6VBasdJF";
        //生成加密密钥
        String key2 = byte2hex(key.getBytes());
        System.out.println(key2);
        String encryptStr = encrypt(str, key2);
        System.out.println(encryptStr);
        System.out.println(decrypt(encryptStr, key2));
    }
}