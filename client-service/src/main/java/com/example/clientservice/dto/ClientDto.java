package com.example.clientservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String status;

    public ClientDto(Long id, @NotNull String name, @NotNull String surname,
                     @NotNull @Email String email, @NotNull String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
    }
}
