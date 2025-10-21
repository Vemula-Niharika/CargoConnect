package com.alpha.abclogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.abclogistics.Entity.Carrier;


@Repository
public interface CarrierRespository  extends JpaRepository<Carrier, Integer> {

}
