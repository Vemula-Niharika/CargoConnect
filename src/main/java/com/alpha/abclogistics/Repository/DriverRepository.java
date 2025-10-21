package com.alpha.abclogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.abclogistics.Entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>  {

}
