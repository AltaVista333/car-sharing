package com.example.carservice.repository;

import com.example.carservice.entity.Car;
import com.example.carservice.entity.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByCarStatus(CarStatus carStatus);

}
