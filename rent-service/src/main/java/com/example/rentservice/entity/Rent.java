package com.example.rentservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Rent {

    @Id
    @SequenceGenerator(name = "rent_generator", sequenceName = "rent_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rent_generator")
    private Long id;

    @NotNull
    private UUID uuid;

    @NotNull
    private Long userId;

    @NotNull
    private Long carId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RentStatus status;

    private LocalDateTime dateTime;

}
