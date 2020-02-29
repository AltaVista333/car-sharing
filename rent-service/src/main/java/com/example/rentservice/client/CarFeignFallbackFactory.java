package com.example.rentservice.client;

import com.example.rentservice.dto.CarDto;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
class CarFeignFallbackFactory implements FallbackFactory<CarFeign> {

    @Override
    public CarFeign create(Throwable cause) {
        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

        return new CarFeign() {
            @Override
            public CarDto getCarById(Long id) {
                log.info("Bad car id");
                return null;
            }
        };



    }
}