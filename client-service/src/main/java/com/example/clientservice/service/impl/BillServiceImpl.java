package com.example.clientservice.service.impl;

import com.example.clientservice.entity.Bill;
import com.example.clientservice.entity.Client;
import com.example.clientservice.exception.ClientNotFoundException;
import com.example.clientservice.exception.DatabeseNotFountException;
import com.example.clientservice.exception.ErrorMessages;
import com.example.clientservice.repository.BillRepository;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private ClientRepository clientRepository;

    public BillServiceImpl(BillRepository billRepository, ClientRepository clientRepository) {
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Page<Bill> getAllBillsByClientId(Pageable pageable, Long clientID) {
        return billRepository.findByClientId(clientID, pageable);
    }

    @Override
    @Transactional
    public Bill addBill(Long clientId, Bill bill) {
        return clientRepository.findById(clientId).map(
                client -> {
                    bill.setClient(client);
                    return billRepository.save(bill);
                }
        ).orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
    }
}
