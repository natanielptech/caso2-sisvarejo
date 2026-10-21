package br.com.bompreco.sisvarejo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SisVarejoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SisVarejoApplication.class, args);
    }
}
