package com.emoOpner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.emoOpner")
@MapperScan(value = "com.emoOpner.mapper")
public class EmoAnalystApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    public static void main(String[] args) {
        SpringApplication.run(EmoAnalystApplication.class, args);
    }
    //修改默认端口8080为8081
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8081);
    }
}
