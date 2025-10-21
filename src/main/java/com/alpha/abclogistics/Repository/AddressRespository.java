package com.alpha.abclogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.abclogistics.Entity.Address;


@Repository
public interface AddressRespository extends JpaRepository<Address, Integer>{
	

}
