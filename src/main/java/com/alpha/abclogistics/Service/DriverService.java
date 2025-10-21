package com.alpha.abclogistics.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.abclogistics.Dto.DriverDto;
import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Entity.Carrier;
import com.alpha.abclogistics.Entity.Driver;
import com.alpha.abclogistics.Entity.Truck;
import com.alpha.abclogistics.Exception.CarrierNotFoundException;
import com.alpha.abclogistics.Exception.DriverAlreadyNOtPresentException;
import com.alpha.abclogistics.Exception.DriverAlreadyPresentException;
import com.alpha.abclogistics.Exception.TruckAlreadyNOtPresentException;
import com.alpha.abclogistics.Repository.CarrierRespository;
import com.alpha.abclogistics.Repository.DriverRepository;
import com.alpha.abclogistics.Repository.TruckRespository;

@Service
public class DriverService {
	@Autowired
	CarrierRespository carrierrepository;
	@Autowired
	TruckRespository truckrepository;
	@Autowired
	DriverRepository driverrepository;
	
	
//	 In DriverService.java
	public ResponseEntity<ResponseStructure<List<Driver>>> getAllDriversResponse() {
	    List<Driver> drivers = driverrepository.findAll();
	    ResponseStructure<List<Driver>> response = new ResponseStructure<>();
	    response.setStatuscode(HttpStatus.OK.value());
	    response.setMessage("All drivers fetched");
	    response.setData(drivers);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	
	
	
	
	
	
	

	public ResponseEntity<ResponseStructure<Driver>> saveDriver(DriverDto driverDto) {
		
		Driver driver = new Driver();
		driver.setId(driverDto.getId());
		driver.setName(driverDto.getName());
		driver.setContact(driverDto.getContact());
	

		   Optional<Driver> dopOptional = driverrepository.findById(driverDto.getId());
		   if(dopOptional.isPresent()) {
			  throw new DriverAlreadyPresentException();
		}
			Driver saveDriver = driverrepository.save(driver);
			ResponseStructure<Driver> responseStructure = new ResponseStructure<Driver>();
			responseStructure.setData(saveDriver);
			responseStructure.setMessage("Driver Saved");
			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Driver>>(responseStructure,HttpStatus.CREATED);
		}

	   public ResponseEntity<ResponseStructure<Driver>> findDriverById(int id) {
		// TODO Auto-generated method stub
		Optional<Driver> dopOptional = driverrepository.findById(id);
		if(!dopOptional.isPresent()) {
			throw new DriverAlreadyNOtPresentException()	;	
	}
		ResponseStructure<Driver> responseStructure= new ResponseStructure<Driver>();
		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Driver with id"+id+"found");
		responseStructure.setData(dopOptional.get());
		
		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure,HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Driver>> deleteDriver(int id) {
		// TODO Auto-generated method stub
		Optional<Driver> dopOptional = driverrepository.findById(id);
		if(!dopOptional.isPresent()) {
			throw new DriverAlreadyNOtPresentException();		
	}
		driverrepository.deleteById(id);
		ResponseStructure<Driver> responseStructure= new ResponseStructure<Driver>();
		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("Driver with id"+id+"deleted");
		responseStructure.setData(dopOptional.get());
		
		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure,HttpStatus.OK);
}
	
	
	public ResponseEntity<ResponseStructure<Driver>> updatedriver(int driverid, int carrierid, int truckid) {
	    Driver founddriver = driverrepository.findById(driverid)
	            .orElseThrow(() -> new DriverAlreadyNOtPresentException());
	    Truck foundtruck = truckrepository.findById(truckid)
	            .orElseThrow(() -> new TruckAlreadyNOtPresentException());
	    Carrier foundcarrier = carrierrepository.findById(carrierid)
	            .orElseThrow(() -> new CarrierNotFoundException());

	    founddriver.setTruck(foundtruck);
	    founddriver.setCarrier(foundcarrier);

	    // Set truck as in use
	    foundtruck.setStatus("inuse");
	    foundtruck.setCarrier(foundcarrier);
	    truckrepository.save(foundtruck);

	    Driver saveddriver = driverrepository.save(founddriver);

	    ResponseStructure<Driver> rs = new ResponseStructure<>();
	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("Driver updated successfully");
	    rs.setData(saveddriver);
	    return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	
	




}
