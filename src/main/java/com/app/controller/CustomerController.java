package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Customer;
import com.app.service.CustomerService;
import com.app.util.CustomerUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController  // creating Rest API
@RequestMapping("/customer")
@SecurityRequirement(name = "JWT-Token")
public class CustomerController {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	// CustomerService s= new CustomerService(); // IOC container
	@Autowired(required = true ) // creating a bean object for below class then we have to annotate the class with autowired
	private CustomerService customerService;

	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Customer customer) {
		Customer addCustomer = null;
		try {
			CustomerUtil.validate(customer);
			addCustomer = customerService.saveCustomer(customer);
			LOGGER.info("Customer added successfully customerName: " + customer.getCustomerName());
		} catch (Exception e) {
			return new ResponseEntity<>("Something wrong while inserting customer: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Customer>(addCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/getCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> customers = null;
		try {
			customers = customerService.getCustomers();
		} catch (Exception e) {
			return new ResponseEntity<>("Something wrong while fetch customer : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int customerId ){
        return customerService.getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Customer customer) {
		Customer updateCustomer = null;
		try {
			CustomerUtil.validate(customer);
			updateCustomer = customerService.updateCustomer(customer);
			LOGGER.info("Customer data updated successfully. customerId : " + customer.getCustomerId());
		} catch (Exception e) {
			return new ResponseEntity<>("Something wrong while update customer :"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Customer>(updateCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		try {
			customerService.deleteCustomer(id);
			LOGGER.info("customer deleted successfully?, customerId:" + id);
		} catch (Exception e) {
			LOGGER.error("customer Not deleted ?, customerId : " + id);
			return new ResponseEntity<>("Something wrong while inserting customer :"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("customer is deleted successfully", HttpStatus.OK);
	}

}
