package org.system.springcloud.msvc.arithmetic.entities.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "request-random-dto")
public class RequestRandomDto {

    private Integer id;
    private String jsonrpc;
    private String method;
    private Params params;

}
