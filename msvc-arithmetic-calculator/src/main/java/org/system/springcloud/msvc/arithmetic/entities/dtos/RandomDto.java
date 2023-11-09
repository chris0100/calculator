package org.system.springcloud.msvc.arithmetic.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RandomDto {
    @JsonProperty("data")
    public List<String> data;
    @JsonProperty("completionTime")
    public String completionTime;
}
