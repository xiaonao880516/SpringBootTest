package com.samuel.springboot.fire;

public class FIRE {
    static int year = 30;
    static double cpi = 0.04;
    static double p = 0.1;//收益率
    static int capital = 5000;//月存
    static int earning = 20000;//每月收益10000元
    static int asset = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("初始资产:"+asset+", 每月存:" + capital + "元, 年化收益率" + p*100 + "%, FIRE目标每月领取:"+earning+"元");
        for (int y = 1; true; y++) {
            for (int month = 1; month <= 12; month++) {
                asset = asset + capital;
            }
            int profit = (int) (asset * p);
            if (profit >= earning*12) {
                System.out.println("第" + y + "年后可实现FIRE, 资产：" + asset);
                break;
            }
            asset = asset + profit;
            System.out.println("第" + y + "年, 资产：" + asset);
        }
    }
}
