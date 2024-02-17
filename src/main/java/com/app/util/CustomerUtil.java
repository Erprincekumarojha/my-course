package com.app.util;

import com.app.entity.Customer;
import com.app.exception.CustomException;

@Generated
public class CustomerUtil {
	
	public static void validate(Customer customer) throws CustomException {
	
		if(customer.getCustomerId()<0) {
			throw new CustomException("please provide the valid customerId");
		}
		if(!customer.getCustomerEmail().contains("@")) {
			throw new CustomException("please provide the valid email");
		}
		if((!(customer.getCustomerNumber().length()>=10 && customer.getCustomerNumber().length()<=13))) {
			throw new CustomException("please provide the valid phoneNumber");
		}
		
	}

}
