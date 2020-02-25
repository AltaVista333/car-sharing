package com.example.rentservice.message;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private UUID uuid;
    private String name;

}
