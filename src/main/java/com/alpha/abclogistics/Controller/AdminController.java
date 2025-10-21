package com.alpha.abclogistics.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.abclogistics.Dto.DriverDto;
import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Dto.TruckDto;
import com.alpha.abclogistics.Dto.UnlaodingDto;
import com.alpha.abclogistics.Entity.Address;
import com.alpha.abclogistics.Entity.Carrier;
import com.alpha.abclogistics.Entity.Driver;
import com.alpha.abclogistics.Entity.Loading;
import com.alpha.abclogistics.Entity.Order;
import com.alpha.abclogistics.Entity.Truck;
import com.alpha.abclogistics.Service.AddressService;
import com.alpha.abclogistics.Service.CarrierService;
import com.alpha.abclogistics.Service.DriverService;
import com.alpha.abclogistics.Service.OrderService;
import com.alpha.abclogistics.Service.TruckService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AddressService addressService;
	@Autowired
	CarrierService carrierService;
	@Autowired
	TruckService truckService;
	@Autowired
	DriverService driverService;
	@Autowired
	OrderService orderService;

	
	@PostMapping("/SaveAddress")
	public ResponseEntity<ResponseStructure<Address>>  saveAddress(@RequestBody Address address)
	{
	
		return addressService.saveAddress(address);
	}
	@PostMapping("/findAddressById/{id}")
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@PathVariable int id) {
		return addressService.findAddressById(id);
	}
	@PostMapping("/deleteAddressBy/{id}")
public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@PathVariable int id) {
	return addressService.deleteAddressById(id);
}
	
	@GetMapping("/getAllAddresses")
	public List<Address> getAllAddresses() {
	    return addressService.getAllAddresses();
	}


	@PostMapping("/saveCarrier")
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@RequestBody Carrier carrier ) {
		
		return carrierService.saveCarrier(carrier);
	}
	@PostMapping("/findCarrierById/{id}")
	public ResponseEntity<ResponseStructure<Carrier>> findCarrierById(@PathVariable int id) {
		return carrierService.findCarrierById(id);
	}
	
	
	@PostMapping("/deleteCarrierById/{id}")
	public ResponseEntity<ResponseStructure<Carrier>> deleteCarrierByIdViaPost(@PathVariable int id) {
	    return carrierService.deleteCarrierById(id);
	}

	
	@GetMapping("/getAllCarriers")
	public List<Carrier> getAllCarriers() {
	    return carrierService.getAllCarriers();
	}
	

	@PostMapping("/saveTruck")
	public ResponseEntity<ResponseStructure<Truck>> saveTruck(@RequestBody TruckDto truckDto) {
		return truckService.savetruck(truckDto);
	}
	@PostMapping("/findTruckById/{id}")
	public ResponseEntity<ResponseStructure<Truck>> findTruckById(@PathVariable int id) {
		return truckService.findtruckById(id);
	}
	@PostMapping("/deleteTruckByid/{id}")
	public ResponseEntity<ResponseStructure<Truck>> deletetruckByid(@PathVariable int id) {
		return truckService.deletetruck(id);
	}
	
	@GetMapping("/getAllTrucks")
	public List<Truck> getAllTrucks() {
	    return truckService.getAllTrucks();
	}

	

	@PostMapping("/saveDriver")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody DriverDto driverDto) {
		return  driverService.saveDriver(driverDto);
	}
	@PostMapping("/findDriverById/{id}")
	public ResponseEntity<ResponseStructure<Driver>> finddriverById(@PathVariable int id) {
	return driverService.findDriverById(id);
	}
	
	@PostMapping("/deleteDriverByid/{id}")
	public ResponseEntity<ResponseStructure<Driver>> deletedriver(@PathVariable int id) {
		return driverService.deleteDriver(id);
	}
	
	@GetMapping("/getAllDrivers")
	public ResponseEntity<ResponseStructure<List<Driver>>> getAllDrivers() {
	    return driverService.getAllDriversResponse();
	}

	

	


@GetMapping("/updatedriver/{driverid}/assigncarrier/{carrierid}/assigntruck/{truckid}")

public ResponseEntity<ResponseStructure<Driver>> updatedriver(@PathVariable int carrierid ,@PathVariable int truckid ,@PathVariable int driverid) {
	
		return driverService.updatedriver(carrierid,truckid,driverid);
	
}










@GetMapping("/getAllOrders")
public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders() {
    List<Order> orders = orderService.getAllOrders(); // create this method in OrderService
    ResponseStructure<List<Order>> response = new ResponseStructure<>();
    response.setStatuscode(HttpStatus.OK.value());
    response.setMessage("All orders fetched");
    response.setData(orders);
    return new ResponseEntity<>(response, HttpStatus.OK);
}




	
	@GetMapping("/updateorderbyid/{orderid}/assigncarrier/{truckid}")
	public ResponseEntity<ResponseStructure<Order>> updateorder(@PathVariable int orderid,@PathVariable int truckid )
	{
		return orderService.updateorder(orderid,truckid);
	}
	

	
	
	@PostMapping("/updateorder/{orderid}/updateloading")
	public ResponseEntity<ResponseStructure<Order>> updateorderupdateloading(@PathVariable int orderid,@RequestBody Loading ldto)
	{
		return  orderService.updateorderupdateloading(orderid,ldto);
	}
	
	@PostMapping("/updateorder/{orderid}/updateUnloading")
	public ResponseEntity<ResponseStructure<Order>> updateorderupdatUnloading(@PathVariable int orderid,@RequestBody UnlaodingDto uldto)
	{
		 return orderService.UpdateOrderUpdateUnloading(orderid,uldto);
	}

	
	
	@GetMapping("/updateorder/{orderid}/assigndriver/{driverid}")
	public ResponseEntity<ResponseStructure<Order>> assignDriverToOrder(@PathVariable int orderid, @PathVariable int driverid) {
	    return orderService.assignDriverToOrder(orderid, driverid);
	}

	


	
	@DeleteMapping("/deleteorder/{orderid}")
	public ResponseEntity<ResponseStructure<String>> deleteOrder(@PathVariable int orderid) {
	    orderService.deleteOrder(orderid);
	    ResponseStructure<String> response = new ResponseStructure<>();
	    response.setStatuscode(HttpStatus.OK.value());
	    response.setMessage("Order deleted successfully");
	    response.setData("Deleted order id: " + orderid);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
