package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.service.TestService;

import java.io.IOException;


@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        TestService runner = ctx.getBean(TestService.class);
        runner.run();
    }
}
