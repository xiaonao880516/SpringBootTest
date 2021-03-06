package com.samuel.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BootApplication {

    @RequestMapping("/")
    String index(){
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "Hello Spring Boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
