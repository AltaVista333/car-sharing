package com.example.clientservice.controller;

import com.example.clientservice.converter.GenericConverter;
import com.example.clientservice.dto.BillDto;
import com.example.clientservice.entity.Bill;
import com.example.clientservice.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("bill")
@Slf4j
public class BillController {

    private BillService billService;
    private GenericConverter<Bill, BillDto> genericConverter;

    public BillController(BillService billService, GenericConverter<Bill, BillDto> genericConverter) {
        this.billService = billService;
        this.genericConverter = genericConverter;
    }

    @GetMapping("/bills/page/{id}")
    public Page<Bill> getAllBillsByClientId(@PathVariable("id") Long clientId,  Pageable pageable){
        return billService.getAllBillsByClientId(pageable, clientId);
    }

    @PostMapping("/addBill/{clientId}")
    public BillDto addBill(@PathVariable("clientId") Long clientId, @RequestBody @Valid BillDto billDto){
        log.info("Client id: {}" , clientId);
        log.info("bill: {}",genericConverter.toEntity(billDto));
        return genericConverter.toDto(
                billService.addBill(clientId, genericConverter.toEntity(billDto)));
    }
}
