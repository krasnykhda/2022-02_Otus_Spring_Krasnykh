package spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.service.IOService;
import spring.service.IOServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    IOService ioService(){
        return new IOServiceImpl(System.out, System.in);
    }



}
