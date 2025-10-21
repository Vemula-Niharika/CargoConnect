package com.alpha.abclogistics.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Dto.TruckDto;
import com.alpha.abclogistics.Entity.Truck;
import com.alpha.abclogistics.Exception.TruckAlreadyNOtPresentException;
import com.alpha.abclogistics.Exception.TruckAlreadyPresentException;
import com.alpha.abclogistics.Repository.TruckRespository;

@Service
public class TruckService {
	@Autowired
	TruckRespository truckrespository;
	
	
	public List<Truck> getAllTrucks() {
	    return truckrespository.findAll();
	}

	public ResponseEntity<ResponseStructure<Truck>> savetruck(TruckDto truckDto) {
	
		Truck truck = new Truck();
		
		truck.setId(truckDto.getId());
		truck.setName(truckDto.getName());
		truck.setNumber(truckDto.getNumber());
		truck.setCapacity(truckDto.getCapacity());
		truck.setStatus(truckDto.getStatus());
	     //carrier
		

		
				
				   Optional<Truck> truckOptional = truckrespository.findById(truckDto.getId());
				   if(truckOptional.isPresent()) {
					  throw new TruckAlreadyPresentException();
				}
					Truck saveTruck = truckrespository.save(truck);
					ResponseStructure<Truck> responseStructure = new ResponseStructure<Truck>();
					responseStructure.setData(saveTruck);
					responseStructure.setMessage("Truck Saved");
					responseStructure.setStatuscode(HttpStatus.CREATED.value());
					return new ResponseEntity<ResponseStructure<Truck>>(responseStructure,HttpStatus.CREATED);
				}

			   public ResponseEntity<ResponseStructure<Truck>> findtruckById(int id) {
				// TODO Auto-generated method stub
				Optional<Truck> truckOptional = truckrespository.findById(id);
				if(!truckOptional.isPresent()) {
					throw new TruckAlreadyPresentException();		
			}
				ResponseStructure<Truck> responseStructure= new ResponseStructure<Truck>();
				responseStructure.setStatuscode(HttpStatus.OK.value());
				responseStructure.setMessage("Truck with id"+id+"found");
				responseStructure.setData(truckOptional.get());
				
				return new ResponseEntity<ResponseStructure<Truck>>(responseStructure,HttpStatus.FOUND);
			}

			public ResponseEntity<ResponseStructure<Truck>> deletetruck(int id) {
				// TODO Auto-generated method stub
				Optional<Truck> truckOptional = truckrespository.findById(id);
				if(!truckOptional.isPresent()) {
					throw new TruckAlreadyNOtPresentException();		
			}
				truckrespository.deleteById(id);
				ResponseStructure<Truck> responseStructure= new ResponseStructure<Truck>();
				responseStructure.setStatuscode(HttpStatus.OK.value());
				responseStructure.setMessage("Truck with id"+id+"deleted");
				responseStructure.setData(truckOptional.get());
				
				return new ResponseEntity<ResponseStructure<Truck>>(responseStructure,HttpStatus.OK);
		  }
		

}
