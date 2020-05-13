package com.samuel.springboot.lkl;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Administrator
 *
 */
public class AES {

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64AAA.decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "bUYJ3nTV6VBasdJF";
        // 需要加密的字串
        String cSrc = "Hello World";
        System.out.println(cSrc);
        // 加密
        String enString = AES.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);
        // 解密
//        12-18 09:23:13.677  2174  3276 D InterceptorUtil: {"code":200,"data":"BwAXPlpDdwgUoRmAYmGqqkW/pK0MJrXLG4BFbMFZJI3LuCGkKVex/6iRcuDhxjUAl1JrtoApUTFmuOYJ6T7x6QXQEo7xh0hbIUcgSdpvktZmeKIJlav4sOOJ5YXgLWR7tsGTRNtDaqToQZCZ4EV+pdYeTk1SxtcQAkRyrOosgxU=","msg":"SUCCESS"}
        String DeString = AES.Decrypt("BwAXPlpDdwgUoRmAYmGqqkW/pK0MJrXLG4BFbMFZJI3LuCGkKVex/6iRcuDhxjUAl1JrtoApUTFmuOYJ6T7x6QXQEo7xh0hbIUcgSdpvktZmeKIJlav4sOOJ5YXgLWR7tsGTRNtDaqToQZCZ4EV+pdYeTk1SxtcQAkRyrOosgxU=", cKey);
        System.out.println("解密后的字串是：" + DeString);
    }
}