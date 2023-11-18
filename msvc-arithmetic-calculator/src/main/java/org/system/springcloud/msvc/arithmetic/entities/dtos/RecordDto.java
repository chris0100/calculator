package org.system.springcloud.msvc.arithmetic.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {

    private Long id;
    private String amount;
    private Integer userBalance;
    private Boolean operationResponse;
    private LocalDateTime date;
    private Long userId;
    private Long operationId;
}
