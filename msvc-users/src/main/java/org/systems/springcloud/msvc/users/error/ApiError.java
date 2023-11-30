package org.systems.springcloud.msvc.users.error;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {


    private String code;
    private String message;
    private String url;

    public ApiError(String code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }
}
