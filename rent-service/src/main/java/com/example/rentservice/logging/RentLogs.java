package com.example.rentservice.logging;

public interface RentLogs {
    String CREATE_RENT = "Request for new rent. CarId: {}. UserId: {}";
    String CLOSE_RENT = "Request for close rent. RentId: {}";
}
