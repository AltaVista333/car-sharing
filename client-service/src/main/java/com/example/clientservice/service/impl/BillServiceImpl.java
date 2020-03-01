package com.example.clientservice.service.impl;

import com.example.clientservice.entity.Bill;
import com.example.clientservice.entity.Client;
import com.example.clientservice.exception.ClientNotFoundException;
import com.example.clientservice.repository.BillRepository;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private ClientRepository clientRepository;

    public BillServiceImpl(BillRepository billRepository, ClientRepository clientRepository) {
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<Bill> getAllBills(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Bill addBill(Long clientId, Bill bill) {
        return clientRepository.findById(clientId).map(
                client -> {
                    bill.setClient(client);
                    return billRepository.save(bill);
                }
        ).orElseThrow(() -> new ClientNotFoundException("Client " + clientId + " not found"));
    }
}