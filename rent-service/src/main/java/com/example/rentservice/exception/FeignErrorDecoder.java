package com.example.rentservice.exception;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                log.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404:
            {
                log.error("Status code " + response.status() + ", methidKey = " + methodKey);
            }
            case 500:
            {
                log.error("Status code " + response.status() + ", methidKey = " + methodKey);
            }
            default:
                return new FeignCustomException("Feignt Exception", HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}
