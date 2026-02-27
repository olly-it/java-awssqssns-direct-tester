package it.olly.awssqssns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("it.olly")
@EntityScan("it.olly")
@EnableScheduling
public class AwsSqsSnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSqsSnsApplication.class, args);
    }

}
