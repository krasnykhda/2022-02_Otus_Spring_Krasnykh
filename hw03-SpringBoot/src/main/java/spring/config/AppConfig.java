package spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import spring.service.IOService;
import spring.service.IOServiceImpl;
import spring.service.MessageSourceService;
import spring.service.MessageSourceServiceImpl;

@Configuration
@PropertySource("application.yml")
public class AppConfig {

    @Bean
    IOService ioService(){
        return new IOServiceImpl(System.out, System.in);
    }



}
