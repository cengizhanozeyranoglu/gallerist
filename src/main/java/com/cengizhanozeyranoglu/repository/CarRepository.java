package com.cengizhanozeyranoglu.repository;

import com.cengizhanozeyranoglu.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
