package com.alpha.abclogistics.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.abclogistics.Dto.LoadingDto;
import com.alpha.abclogistics.Dto.OrderDto;
import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Dto.UnlaodingDto;
import com.alpha.abclogistics.Entity.Address;
import com.alpha.abclogistics.Entity.Cargo;
import com.alpha.abclogistics.Entity.Driver;
import com.alpha.abclogistics.Entity.Loading;
import com.alpha.abclogistics.Entity.Order;
import com.alpha.abclogistics.Entity.Truck;
import com.alpha.abclogistics.Entity.Unloading;
import com.alpha.abclogistics.Exception.CargoWeightIsMoreThanTruckCapacityException;
import com.alpha.abclogistics.Exception.DriverAlreadyPresentException;
import com.alpha.abclogistics.Exception.LoadingAddressNotPresenting;
import com.alpha.abclogistics.Exception.OrderIdNotPresentException;
import com.alpha.abclogistics.Exception.SameAddressNotPossibleException;
import com.alpha.abclogistics.Exception.TruckNotPresent;
import com.alpha.abclogistics.Exception.UnloadingAddressNotPresenting;
import com.alpha.abclogistics.Repository.AddressRespository;
import com.alpha.abclogistics.Repository.DriverRepository;
import com.alpha.abclogistics.Repository.OrderRespository;
import com.alpha.abclogistics.Repository.TruckRespository;

@Service
public class OrderService {
	@Autowired
	AddressRespository addressRespository;
	@Autowired
	OrderRespository orderrespository;
	@Autowired
	TruckRespository truckRespository;
	@Autowired
	MailServices mailservice;
	
	
	
	
	public void deleteOrder(int orderid) {
	    Order order = orderrespository.findById(orderid)
	            .orElseThrow(() -> new OrderIdNotPresentException());
	    orderrespository.delete(order);
	}

	
	public List<Order> getAllOrders() {
	    return orderrespository.findAll(); // assuming you have OrderRepository
	}

	
	public ResponseEntity<ResponseStructure<Order>> placeuserorder(OrderDto orderDto) {

	    // Step 1: Prepare the order object (your existing logic)
	    Order order = new Order();
	    order.setId(orderDto.getId());
	    order.setOrderdate(orderDto.getOrderdate());
	    order.setMail(orderDto.getMail());
	    int cost = 10 * (orderDto.getCargoweight() * orderDto.getCargocount());
	    order.setCost(cost);

	    Cargo cargo = new Cargo();
	    cargo.setId(orderDto.getCargoid());
	    cargo.setName(orderDto.getCargoname());
	    cargo.setDescription(orderDto.getCargodescription());
	    cargo.setWeight(orderDto.getCargoweight());
	    cargo.setCount(orderDto.getCargocount());
	    order.setCargo(cargo);

	    Loading loading = new Loading();
	    Address loadaddress = addressRespository.findById(orderDto.getLoadingaddid())
	        .orElseThrow(() -> new LoadingAddressNotPresenting());
	    loading.setAddress(loadaddress);
	    order.setLoading(loading);

	    Unloading unloading = new Unloading();
	    Address unloadadd = addressRespository.findById(orderDto.getUnloadingaddid())
	        .orElseThrow(() -> new UnloadingAddressNotPresenting());
	    unloading.setAddress(unloadadd);
	    order.setUnloading(unloading);

	    if (loadaddress == unloadadd) {
	        throw new SameAddressNotPossibleException();
	    }

	    // ✅ Step 2: Check available trucks before saving order
	    List<Truck> allTrucks = truckRespository.findAll();
	    boolean canCarry = allTrucks.stream()
	        .anyMatch(truck -> truck.getCapacity() >= cargo.getWeight());

	    if (!canCarry) {
	        // None of the trucks can carry this cargo
	        throw new CargoWeightIsMoreThanTruckCapacityException();
	    }

	    // ✅ Step 3: Proceed normally (send mail + save order)
	    String subject = "ORDER PLACED";
	    String content = "Your order placed from " + 
	        order.getLoading().getAddress().getCity() + " to " + 
	        order.getUnloading().getAddress().getCity();

	    mailservice.sendMail(order.getMail(), subject, content);

	    Order savedOrder = orderrespository.save(order);

	    ResponseStructure<Order> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatuscode(HttpStatus.CREATED.value());
	    responseStructure.setMessage("ORDER PLACED SUCCESSFULLY");
	    responseStructure.setData(savedOrder);

	    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	
	
public ResponseEntity<ResponseStructure<Order>> updateorder(int orderid, int truckid) {
			Order order=orderrespository.findById(orderid).orElseThrow(()-> new OrderIdNotPresentException());
		Cargo cargo=order.getCargo();
		int cargoWeight=cargo.getWeight();
		Truck truck=truckRespository.findById(truckid).orElseThrow(()->new TruckNotPresent());
		int truckcapacity=truck.getCapacity();
		if(cargoWeight>truckcapacity)
		{
			throw new CargoWeightIsMoreThanTruckCapacityException();
		}
		


		else
		{
			order.setCarrier(truck.getCarrier());
			
		}
		
		Order updatedorder=orderrespository.save(order);
		
		String subject="Name"+order.getCarrier().getName()+"Phone"+order.getCarrier().getContact()+"  is a person assingned to youe order";
	String Content=order.getCarrier().getName();
		
          mailservice.sendMail(order.getMail(), subject, Content);
		
		
		ResponseStructure<Order> rs= new ResponseStructure<Order>();
	rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Order Updated");
		rs.setData(updatedorder);
		return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.OK);
	}

	
	
@Autowired
private DriverRepository driverRespository;  // Add this if not already

public ResponseEntity<ResponseStructure<Order>> assignDriverToOrder(int orderId, int driverId) {
    Optional<Order> optionalOrder = orderrespository.findById(orderId);
    Optional<Driver> optionalDriver = driverRespository.findById(driverId);

    if (!optionalOrder.isPresent()) {
        throw new OrderIdNotPresentException();
    }
    if (!optionalDriver.isPresent()) {
        throw new RuntimeException("Driver not found with ID: " + driverId);
    }

    Order order = optionalOrder.get();
    Driver driver = optionalDriver.get();

    // Assign driver, truck, and carrier to order
    order.setDriver(driver);
    if (driver.getTruck() != null) {
        order.setCarrier(driver.getTruck().getCarrier());
    }

    // Save updated order
    Order updatedOrder = orderrespository.save(order);

    // Send confirmation email
    String subject = "Driver Assigned for Your Order";
    String content = "Dear user,\n\nDriver " + driver.getName() + " has been assigned to your order (ID: " 
        + order.getId() + ").\n\nTruck: " 
        + (driver.getTruck() != null ? driver.getTruck().getNumber() : "N/A") +
        "\nCarrier: " 
        + (driver.getTruck() != null && driver.getTruck().getCarrier() != null 
            ? driver.getTruck().getCarrier().getName() 
            : "N/A") + "\n\nThank you.";
    mailservice.sendMail(order.getMail(), subject, content);

    ResponseStructure<Order> response = new ResponseStructure<>();
    response.setStatuscode(HttpStatus.OK.value());
    response.setMessage("Driver assigned to order successfully");
    response.setData(updatedOrder);

    return new ResponseEntity<>(response, HttpStatus.OK);
}


public ResponseEntity<ResponseStructure<Order>> updateorderupdateloading(int orderid, LoadingDto ldto) {
    Order order = orderrespository.findById(orderid)
            .orElseThrow(() -> new OrderIdNotPresentException());

    Loading loading = order.getLoading();
    if (loading == null) {
        loading = new Loading();
        order.setLoading(loading);
    }

    loading.setDate(ldto.getDate());
    loading.setTime(ldto.getTime());

    orderrespository.save(order);

    String subject = "Your order is loaded";
    String content = "Order loaded on Date " + loading.getDate() + " at Time " + loading.getTime();
    mailservice.sendMail(order.getMail(), subject, content);

    ResponseStructure<Order> rs = new ResponseStructure<>();
    rs.setStatuscode(HttpStatus.OK.value());
    rs.setMessage("Loading updated successfully");
    rs.setData(order);
    return new ResponseEntity<>(rs, HttpStatus.OK);
}


public ResponseEntity<ResponseStructure<Order>> UpdateOrderUpdateUnloading(int orderid, UnlaodingDto uldto) {
    Order order = orderrespository.findById(orderid)
            .orElseThrow(() -> new OrderIdNotPresentException());

    Unloading unloading = order.getUnloading();
    if (unloading == null) {
        unloading = new Unloading();
        order.setUnloading(unloading);
    }

    unloading.setDate(uldto.getDate());
    unloading.setTime(uldto.getTime());
    order.setStatus("Completed");

    orderrespository.save(order);

    String sub = "Your order is unloaded";
    String content = "Order unloaded on Date " + unloading.getDate() + " at Time " + unloading.getTime();
    mailservice.sendMail(order.getMail(), sub, content);

    ResponseStructure<Order> rs = new ResponseStructure<>();
    rs.setMessage("Unloading updated successfully");
    rs.setStatuscode(HttpStatus.ACCEPTED.value());
    rs.setData(order);
    return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
}


}
