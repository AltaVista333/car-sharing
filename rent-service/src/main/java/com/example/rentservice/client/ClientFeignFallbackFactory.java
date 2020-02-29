package com.example.rentservice.client;

import com.example.rentservice.dto.CarDto;
import com.example.rentservice.dto.ClientDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientFeignFallbackFactory implements FallbackFactory<ClientFeign> {


    @Override
    public ClientFeign create(Throwable cause) {
        return new ClientFeign() {
            @Override
            public ClientDto getUserByID(Long id) {
                log.info("bad client id");
                return null;
            }
        };
    }
}
