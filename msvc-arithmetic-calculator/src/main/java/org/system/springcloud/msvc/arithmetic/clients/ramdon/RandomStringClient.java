package org.system.springcloud.msvc.arithmetic.clients.ramdon;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.system.springcloud.msvc.arithmetic.entities.dtos.RequestRandomDto;
import org.system.springcloud.msvc.arithmetic.entities.dtos.ResponseRandomDto;

@FeignClient(url = "https://api.random.org/json-rpc/2/invoke", name = "random")
public interface RandomStringClient {

    String CONTENT_TYPE="application/json";

    @PostMapping
    ResponseRandomDto generateRandomStrings(@RequestHeader(name = CONTENT_TYPE) String contentType,
                              @RequestBody RequestRandomDto requestRandomDto);
}
