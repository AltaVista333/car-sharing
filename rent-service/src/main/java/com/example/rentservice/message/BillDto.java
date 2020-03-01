package com.example.rentservice.message;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDto {

    @NotNull
    private Long rentId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Boolean paidOff;
}
