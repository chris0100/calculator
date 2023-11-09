package org.system.springcloud.msvc.arithmetic.entities.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "request-random-dto.params")
public class Params {

    private String apiKey;
    private Integer n;
    private Integer length;
    private String characters;
    private Boolean replacement;


}
