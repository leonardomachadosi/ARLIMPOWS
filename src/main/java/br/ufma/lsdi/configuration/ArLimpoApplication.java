package br.ufma.lsdi.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients(basePackages = {"br.ufma.lsdi"})
@SpringBootApplication(scanBasePackages = {"br.ufma.lsdi"})
public class ArLimpoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ArLimpoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ArLimpoApplication.class);
    }
}