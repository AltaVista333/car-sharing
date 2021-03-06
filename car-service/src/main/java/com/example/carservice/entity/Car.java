package com.example.carservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Car {

    @Id
    @SequenceGenerator(name = "car_generator", sequenceName = "car_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_generator")
    private Long id;

    @NotNull
    private String model;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @NotNull
    private Integer coordX;

    @NotNull
    private Integer coordY;

    @NotNull
    private BigDecimal minutePrice;

}
