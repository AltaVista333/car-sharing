package com.example.rentservice.message;

import lombok.*;

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
