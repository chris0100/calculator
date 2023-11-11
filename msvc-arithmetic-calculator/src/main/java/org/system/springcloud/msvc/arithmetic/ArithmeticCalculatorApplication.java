package org.system.springcloud.msvc.arithmetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ConfigurationPropertiesScan
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ArithmeticCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArithmeticCalculatorApplication.class, args);
    }
}
