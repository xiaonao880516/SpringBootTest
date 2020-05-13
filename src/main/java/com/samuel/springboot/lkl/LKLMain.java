package com.samuel.springboot.lkl;

import com.alibaba.fastjson.JSON;
import com.samuel.springboot.entity.LoginUser;
import com.samuel.springboot.lkl.lklsign.LKLApiException;
import com.samuel.springboot.lkl.lklsign.LKLSignature;
import com.samuel.springboot.lkl.util.MD5Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class LKLMain {




    public static void main(String[] args) {
//        LoginUser user = new LoginUser();
//        user.setId(11);
//        user.setPassword("111");
//        user.setReal_name("mv");
//        user.setUsername("孟伟");
//        System.out.println(JSON.toJSON(user));
//        String aa = "{\"password\":\"111\",\"real_name\":\"mv\",\"id\":11,\"username\":\"孟伟\"}";
//        JSON.parseObject(aa, LoginUser.class);



        String result = "{\"data\":\"150%61%143%141%124%137%142%124%126%143%132%138%137%122%132%127%61%85%61%76%83%75%78%76%84%77%76%81%82%83%79%82%79%61%71%61%141%128%129%128%141%137%144%136%125%128%141%61%85%61%75%78%76%84%77%76%81%82%83%79%82%79%61%71%61%139%124%148%122%124%136%143%61%85%61%75%75%75%75%75%75%75%75%75%75%75%75%75%75%76%61%71%61%138%141%127%128%141%132%127%122%142%126%124%137%61%85%61%79%77%75%75%75%75%75%75%81%84%77%75%76%83%75%78%76%84%76%81%75%84%82%82%80%82%77%78%61%71%61%125%124%137%134%122%143%148%139%128%61%85%61%59%61%71%61%138%139%128%137%132%127%61%85%61%61%71%61%142%148%142%143%141%124%126%128%137%138%61%85%61%75%75%75%75%75%83%61%71%61%136%126%131%122%132%127%61%85%61%83%77%77%77%84%75%75%80%83%76%77%79%84%80%84%61%71%61%127%128%145%132%126%128%122%132%137%129%138%61%85%61%84%79%79%79%82%77%75%84%61%71%61%138%144%143%122%143%141%124%127%128%122%137%138%61%85%61%77%75%76%83%75%78%76%84%76%81%77%82%78%84%75%76%61%71%61%126%124%141%127%122%137%138%61%85%61%76%78%79%80%77%80%69%69%69%69%69%69%69%69%80%78%79%78%61%71%61%125%124%143%126%131%125%132%135%135%137%138%61%85%61%75%75%75%75%75%77%61%71%61%126%138%144%139%138%137%122%129%128%128%61%85%61%59%61%71%61%143%138%143%124%135%122%129%128%128%61%85%61%75%75%75%75%75%75%75%75%75%75%75%75%75%75%76%61%71%61%143%141%124%127%128%122%143%148%139%128%61%85%61%75%76%77%79%75%76%61%71%61%141%128%142%144%135%143%122%126%138%127%128%61%85%61%110%112%94%94%96%110%110%61%71%61%139%124%148%122%143%148%139%128%61%85%61%84%76%61%71%61%143%132%136%128%122%128%137%127%61%85%61%77%75%76%83%75%78%76%84%76%81%77%83%75%82%61%152%\",\"sign\":\"hpshVvKFLygVZkvoFi9rBRZtJAciBBkFFM/34HlcsX86utJsB3w2MeiwUJGvwBWHEwJd7zsxG32243wusSxhx3TYQ8K5RVBmGJkj7tKf7qMA6Ez0h18KSvH3+n0rmGY9hNO6yRziwNO+1T2xmShbSAYNVYhHzyb9WGQKCf4sSZs=\"}";
        Map<String,String> resultMap = JSON.parseObject(result, Map.class);
//        for(String key : resultMap.keySet()){
//            System.out.println("key=" + key);
//            System.out.println("value=" + resultMap.get(key));
//        }


        /*
         * 获取密文，并解密
         */
        String ciper = resultMap.get("data");
        String ss = MD5Util.decrypt(ciper);	//解密
        System.out.println("解密后的结果:" + ss);
        Map<String,String> dataMap = JSON.parseObject(ss, Map.class);
        dataMap.put("sign", resultMap.get("sign"));
//        for(String key : dataMap.keySet()){
//            System.out.println("key=" + key);
//            System.out.println("value=" + dataMap.get(key));
//        }


        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyEGpVK0OR3YqSHep6qS9D1NFC9FYR0qRiiipbzxQCiBRXRAT3E6EQDAQP6RN+jEhl2X+BVeYg+HowrtY56nLUievoTu2FFc16PZnvjygZL0n7ZySpXVpib7rFbCt3UByuHhoMEjUBiSPgsVRnmquWMTsKOVqYXO9mybYt83BmBQIDAQAB";

        //验签
        boolean rsaCheck = false;
        try {
            rsaCheck = LKLSignature.rsaCheckV1(dataMap, publicKey, "UTF-8");
            System.out.println("验签结果"+rsaCheck);
        } catch (LKLApiException e) {
            e.printStackTrace();
        }

    }
}
