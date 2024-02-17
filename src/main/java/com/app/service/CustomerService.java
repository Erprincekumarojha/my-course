package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer customer) throws Exception;

	List<Customer> getCustomers() throws Exception;

	Customer updateCustomer(Customer customer) throws Exception;

	void deleteCustomer(int id) throws Exception;

	Optional<Customer> getCustomerById(int customerId);

}
