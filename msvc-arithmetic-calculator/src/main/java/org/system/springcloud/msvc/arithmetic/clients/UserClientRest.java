package org.system.springcloud.msvc.arithmetic.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.system.springcloud.msvc.arithmetic.entities.dtos.UserStatusDto;

@FeignClient(url = "${msvc.users.url}", name = "msvc-users")
public interface UserClientRest {

    @GetMapping("/api/users/validateStatus/{userId}")
    UserStatusDto validateStatusUser(@PathVariable(name = "userId") Long userId);
}
