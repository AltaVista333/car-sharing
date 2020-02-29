package com.example.rentservice.repository;

import com.example.rentservice.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Optional<Rent> findById(Long id);

    @Query(nativeQuery = true, value = "select count(*) from rent where user_id = :userId and status = 'ONGOING'")
    Long existClientActiveRent(@Param("userId") Long userId);


}
