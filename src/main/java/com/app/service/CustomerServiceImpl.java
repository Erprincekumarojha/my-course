package com.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.entity.Customer;
import com.app.exception.CustomException;
import com.app.repository.CustomerRepository;
import com.app.util.Generated;

@Generated
@Service // Stereotype annotation // 
public class CustomerServiceImpl implements CustomerService {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@org.springframework.beans.factory.annotation.Autowired(required = true)
	private CustomerRepository repository;

	@Override
	public Customer saveCustomer(Customer customer) throws Exception {
		try {
			customer = repository.save(customer);
			LOGGER.info("Customer successfully inserted in DB, customerName : " + customer.getCustomerName());
		} catch (Exception e) {
			throw new CustomException("Sorry customer is not inserted in DB, customerId : " + customer.getCustomerId());
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomers() throws Exception {
		List<Customer> customers = null;
		try {
			customers = repository.findAll();
			LOGGER.info("all customer record are selected: getCustomer() {}");
		} catch (Exception e) {
			throw new CustomException("Sorry customer is not fetched, something wrong in server-side");
		}
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws Exception {
		try {
			Customer checkcCustomer = repository.getByCustomerId(customer.getCustomerId());
			if (checkcCustomer == null) {
				LOGGER.error("Sorry given customerId with customer is not exist");
				throw new CustomException("Sorry given customerId with customer is not exist");
			}
			LOGGER.info("customer update request is submited");
			checkcCustomer.setCustomerName(customer.getCustomerName());
			checkcCustomer.setCustomerEmail(customer.getCustomerEmail());
			checkcCustomer.setCustomerNumber(customer.getCustomerNumber());
			checkcCustomer.setTitle(customer.getTitle());
			checkcCustomer.setDept(customer.getDept());
		} catch (Exception e) {
			LOGGER.error("Sorry given customerId with customer is not exist");
			throw new Exception("Sorry given customerId with customer is not exist");
		}
		return repository.saveAndFlush(customer);
	}

	@Override
	public void deleteCustomer(int id) throws Exception {
		try {
			if (id < 0) {
				throw new CustomException("customerId should not be -ve or zero");
			}
			Customer customer = repository.getByCustomerId(id);
			if (customer != null)
				repository.deleteById(id);
			LOGGER.info("customer deleted successfuly, customerName : " + customer.getCustomerName());
		} catch (Exception e) {
			LOGGER.error("Sorry given customerId with customer is not exist");
			throw new CustomException("Sorry given customerId with customer is not exist");
		}

	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		return repository.findById(customerId);
	}	

}
