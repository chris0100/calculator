package org.system.springcloud.msvc.arithmetic.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.system.springcloud.msvc.arithmetic.entities.dtos.UserDto;

@FeignClient(url = "http://localhost:8002", name = "msvc-users")
public interface UserClientRest {

    @GetMapping("/api/validateStatus/{userId}")
    UserDto validateStatusUser(@PathVariable Long userId);
}
