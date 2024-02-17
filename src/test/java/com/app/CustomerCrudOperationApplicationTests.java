package com.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerCrudOperationApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void mainMethodTest() {
	  
		CustomerCrudOperationApplication s= new CustomerCrudOperationApplication();
		s.main(new String[] {"java", "spring"});
	}

}
