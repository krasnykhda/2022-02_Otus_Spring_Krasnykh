package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.service.TestService;

import java.io.IOException;


@Configuration
@ComponentScan
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        TestService runner = ctx.getBean(TestService.class);
        runner.run();
    }
}
