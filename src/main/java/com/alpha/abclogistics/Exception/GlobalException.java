package com.alpha.abclogistics.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.abclogistics.Dto.ResponseStructure;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(AddressIsAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> AddressIsAlreadyPresentException(AddressIsAlreadyPresentException ex) {
		
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		resStructure.setMessage("Address is Already Present");
		resStructure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.ALREADY_REPORTED);
		
	}
	
	
	
	@ExceptionHandler(CargoWeightIsMoreThanTruckCapacityException.class)
	public ResponseEntity<ResponseStructure<String>> handleCargoWeightException(CargoWeightIsMoreThanTruckCapacityException ex) {
	    ResponseStructure<String> resStructure = new ResponseStructure<>();
	    resStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
	    resStructure.setMessage("Cargo weight exceeds truck capacity");
	    resStructure.setData(ex.getMessage()); // optionally include cargo and truck weight info
	    return new ResponseEntity<>(resStructure, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> AddressNotFoundException(AddressNotFoundException ex) {
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		resStructure.setMessage("Address is not found");
		resStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(CarrierisAlreadyIsProcced.class)
	public ResponseEntity<ResponseStructure<String>> CarrierisAlreadyIsProcced(CarrierisAlreadyIsProcced ex) {
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		resStructure.setMessage("carrier is  found");
		resStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.ALREADY_REPORTED);
		
	}
	
	@ExceptionHandler(CarrierNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> CarrierNotFoundException(CarrierNotFoundException ex) {
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		resStructure.setMessage("carrier is not found");
		resStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(DriverAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> DriverAlreadyPresentException(DriverAlreadyPresentException ex) {
		
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		resStructure.setMessage("Driver is Already Present");
		resStructure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.ALREADY_REPORTED);
		
	}
	@ExceptionHandler(DriverAlreadyNOtPresentException.class)
	public ResponseEntity<ResponseStructure<String>> DriverAlreadyNOtPresentException(DriverAlreadyNOtPresentException ex) {
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		resStructure.setMessage("Driver is not found");
		resStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(LoadingAddressNotPresenting.class)
	public ResponseEntity<ResponseStructure<String>> LoadingAddressNotPresenting(LoadingAddressNotPresenting ex) {
		
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		resStructure.setMessage("Loading Not Present");
		resStructure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(UnloadingAddressNotPresenting.class)
	public ResponseEntity<ResponseStructure<String>> UnloadingAddressNotPresenting(UnloadingAddressNotPresenting ex) {
		
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		resStructure.setMessage("Unloading not Present");
		resStructure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(TruckAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> TruckAlreadyPresentException(TruckAlreadyPresentException ex) {
		
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		resStructure.setMessage("Truck is Already Present");
		resStructure.setData(null);
		
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.ALREADY_REPORTED);
		
	}
	@ExceptionHandler(TruckAlreadyNOtPresentException.class)
	public ResponseEntity<ResponseStructure<String>> TruckAlreadyNOtPresentException(TruckAlreadyNOtPresentException ex) {
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		resStructure.setMessage("Truck is not found");
		resStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(SameAddressNotPossibleException.class)
	public ResponseEntity<ResponseStructure<String>> SameAddressNotPossibleException(SameAddressNotPossibleException ex) {
		
		ResponseStructure<String> resStructure = new ResponseStructure<String>();
		resStructure.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		resStructure.setMessage("SameAddressNotPossibleException");
		resStructure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(resStructure,HttpStatus.NOT_ACCEPTABLE);
		
	}
	

	
}
