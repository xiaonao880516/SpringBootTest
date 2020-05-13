package com.samuel.springboot.fire;

public class Test {


    public static void main(String[] args) throws Exception {
        double rate = 0.2;
        double money = 5000;
        for(int i=0;i<30;i++){
            money = money + money*rate;
        }

        System.out.println(money);

    }
}
