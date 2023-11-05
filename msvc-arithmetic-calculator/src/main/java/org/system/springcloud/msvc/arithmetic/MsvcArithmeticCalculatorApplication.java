package org.system.springcloud.msvc.arithmetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MsvcArithmeticCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcArithmeticCalculatorApplication.class, args);
    }
}
