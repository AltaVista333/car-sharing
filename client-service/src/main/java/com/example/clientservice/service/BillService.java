package com.example.clientservice.service;

import com.example.clientservice.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BillService {

    Page<Bill> getAllBillsByClientId(Pageable pageable, Long clientId);
    Bill addBill(Long postId, Bill bill);
}
