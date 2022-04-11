package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import spring.service.IOService;
import spring.service.IOServiceImpl;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    IOService ioService(){
        return new IOServiceImpl(System.out, System.in);
    }



}
