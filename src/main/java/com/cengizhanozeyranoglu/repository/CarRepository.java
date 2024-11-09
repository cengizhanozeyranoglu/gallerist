package com.cengizhanozeyranoglu.repository;

import com.cengizhanozeyranoglu.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
