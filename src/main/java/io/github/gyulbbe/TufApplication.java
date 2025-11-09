package io.github.gyulbbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TufApplication {

    public static void main(String[] args) {
        SpringApplication.run(TufApplication.class, args);
    }

}
