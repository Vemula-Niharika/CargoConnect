package com.alpha.abclogistics.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Entity.Carrier;
import com.alpha.abclogistics.Exception.CarrierNotFoundException;
import com.alpha.abclogistics.Exception.CarrierisAlreadyIsProcced;
import com.alpha.abclogistics.Repository.CarrierRespository;

@Service
public class CarrierService {

    @Autowired
    CarrierRespository carrierRespository;

    // Get all carriers
    public List<Carrier> getAllCarriers() {
        return carrierRespository.findAll();
    }

    // Save carrier
    public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(Carrier carrier) {
        Optional<Carrier> cOptional = carrierRespository.findById(carrier.getId());
        if (cOptional.isPresent()) {
            throw new CarrierisAlreadyIsProcced();
        }
        Carrier savedCarrier = carrierRespository.save(carrier);
        ResponseStructure<Carrier> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.CREATED.value());
        rs.setMessage("Carrier saved successfully");
        rs.setData(savedCarrier);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    // Find carrier by ID
    public ResponseEntity<ResponseStructure<Carrier>> findCarrierById(int id) {
        Carrier carrier = carrierRespository.findById(id)
                .orElseThrow(() -> new CarrierNotFoundException());
        ResponseStructure<Carrier> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Carrier with ID " + id + " found");
        rs.setData(carrier);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    // Delete carrier by ID
    public ResponseEntity<ResponseStructure<Carrier>> deleteCarrierById(int id) {
        Carrier carrierFound = carrierRespository.findById(id)
                .orElseThrow(() -> new CarrierNotFoundException());
        carrierRespository.delete(carrierFound);

        ResponseStructure<Carrier> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Carrier with ID " + id + " deleted successfully");
        rs.setData(carrierFound);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
