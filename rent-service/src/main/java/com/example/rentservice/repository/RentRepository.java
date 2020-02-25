package com.example.rentservice.repository;

import com.example.rentservice.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent findByUuid(@NotNull UUID uuid);

}
