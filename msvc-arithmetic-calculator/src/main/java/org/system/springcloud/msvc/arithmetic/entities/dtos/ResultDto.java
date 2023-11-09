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
public class ResultDto {

    @JsonProperty("random")
    public RandomDto random;
    @JsonProperty("bitsUsed")
    public int bitsUsed;
    @JsonProperty("bitsLeft")
    public int bitsLeft;
    @JsonProperty("requestsLeft")
    public int requestsLeft;
    @JsonProperty("advisoryDelay")
    public int advisoryDelay;
}
