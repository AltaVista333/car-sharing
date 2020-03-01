package com.example.clientservice.repository;

import com.example.clientservice.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Page<Bill> findByClientId(Long clientId, Pageable pageble);
    Optional<Bill> findByIdAndClientId(Long id, Long clientId);

}
