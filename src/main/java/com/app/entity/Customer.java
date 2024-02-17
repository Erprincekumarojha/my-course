package com.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@SuppressWarnings("serial")
@Entity
@Builder
@Table(name = "customer")
public class Customer implements Serializable {
	
	@Id
	private int customerId;
	
	private String customerName;
	private String title;
	private String customerEmail;
	private String customerNumber;
	private String dept;

	public Customer(int customerId, String customerName, String title, String customerEmail, String customerNumber,
			String dept) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.title = title;
		this.customerEmail = customerEmail;
		this.customerNumber = customerNumber;
		this.dept = dept;
	}

	public Customer() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
