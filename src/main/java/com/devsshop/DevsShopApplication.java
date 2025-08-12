package com.devsshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DevsShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevsShopApplication.class, args);
    }

}
