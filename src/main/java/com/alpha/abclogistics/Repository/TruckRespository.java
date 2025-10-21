package com.alpha.abclogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.abclogistics.Entity.Truck;

@Repository
public interface TruckRespository extends JpaRepository<Truck, Integer>{

}
