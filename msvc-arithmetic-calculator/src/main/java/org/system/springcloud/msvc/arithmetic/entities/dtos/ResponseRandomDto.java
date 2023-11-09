package org.system.springcloud.msvc.arithmetic.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRandomDto {

    @JsonProperty("jsonrpc")
    public String jsonrpc;
    @JsonProperty("result")
    public ResultDto result;
    @JsonProperty("id")
    public int id;
}
