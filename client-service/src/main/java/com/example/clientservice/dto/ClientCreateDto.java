package com.example.clientservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientCreateDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    public ClientCreateDto(@NotNull String name, @NotNull String surname, @NotNull @Email String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
