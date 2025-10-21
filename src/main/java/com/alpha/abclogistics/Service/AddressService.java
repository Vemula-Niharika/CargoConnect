package com.alpha.abclogistics.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Entity.Address;
import com.alpha.abclogistics.Exception.AddressIsAlreadyPresentException;
import com.alpha.abclogistics.Exception.AddressNotFoundException;
import com.alpha.abclogistics.Repository.AddressRespository;

@Service
public class AddressService {
	@Autowired
	AddressRespository addressrepository;
	
	
	public List<Address> getAllAddresses() {
	    return addressrepository.findAll();
	}

	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		
		Optional<Address> addressoptional = addressrepository.findById(address.getId());
		
		if(addressoptional.isPresent()) {
			throw new AddressIsAlreadyPresentException();
		}
		else {
			ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
			
			Address savedaddress = addressrepository.save(address);
			
			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Address Stored");
			responseStructure.setData(savedaddress);
			
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.CREATED);
		}
		
	}

	public ResponseEntity<ResponseStructure<Address>> findAddressById(int id) {
		
		
		Optional<Address> optinaladdress=addressrepository.findById(id);
		
		if(!optinaladdress.isPresent()) {
			throw new AddressNotFoundException();
		}else {
			
			ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Address with id "+id+" is Found");
			responseStructure.setData(optinaladdress.get());
			
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(int id) {
		
		Address  addressfound= addressrepository.findById(id).orElseThrow(()->new AddressNotFoundException());
		
		addressrepository.delete(addressfound);
		
		ResponseStructure<Address> resStructure = new ResponseStructure<Address>();
		resStructure.setStatuscode(HttpStatus.FOUND.value());
		resStructure.setMessage("Address with id :" + id +" is deleted");
		resStructure.setData(addressfound);
		
		return new ResponseEntity<ResponseStructure<Address>>(resStructure,HttpStatus.FOUND);
	}
}
