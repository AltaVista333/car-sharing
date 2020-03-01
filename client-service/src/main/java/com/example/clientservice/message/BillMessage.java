package com.example.clientservice.message;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillMessage {

    private Long rentId;

    private Long clientId;

    private BigDecimal amount;

    private Boolean paidOff;

}
