package org.system.springcloud.msvc.arithmetic.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class Config {


    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
