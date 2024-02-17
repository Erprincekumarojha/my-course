package com.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.app.entity.Customer;
import com.app.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest
public class CustomerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private static String Common_URL = "http://localhost:8096/customer";

    @Test
    public void testSave() throws Exception{

        // prepered a object
        Customer customer = Customer.builder()
        		.customerId(101)
        		.customerName("Prince Kumar")
        		.customerEmail("prince@gmail.com")
        		.customerNumber("9131169055")
        		.title("Mr")
        		.dept("HR").build();
         
        // hit the service method and mock request value
        given(customerService.saveCustomer(any(Customer.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        
        ResultActions response = mockMvc.perform(post("/customer/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)));

        // then - verify the result 
       // response.andDo(print()).
               // andExpect(status().isCreated());
        
        response.andDo(print()).
        andExpect(status().isCreated())
        .andExpect(jsonPath("$.customerId",
                is(customer.getCustomerId())))
        .andExpect(jsonPath("$.customerName",
                is(customer.getCustomerName())))
        .andExpect(jsonPath("$.title",
                is(customer.getTitle())))
        .andExpect(jsonPath("$.customerNumber",
                is(customer.getCustomerNumber())))
        .andExpect(jsonPath("$.dept",
                is(customer.getDept())))
        .andExpect(jsonPath("$.customerEmail",
                is(customer.getCustomerEmail())));

    }
    
    @Test
    public void getAllCustomersTest() throws Exception{
        // given - Data for test
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder()
        		.customerId(104)
        		.customerName("Prince Kumar")
        		.customerEmail("prince@gmail.com")
        		.customerNumber("9131169055")
        		.title("Mr")
        		.dept("HR").build());
        customers.add(Customer.builder()
        		.customerId(105)
        		.customerName("Deepak Kumar")
        		.customerEmail("Deepak@gmail.com")
        		.customerNumber("7415578945")
        		.title("Ojha")
        		.dept("DEV").build());
        
        given(customerService.getCustomers()).willReturn(customers);

        // when -  test the controller while calling
        ResultActions response = mockMvc.perform(get("/customer/getCustomers"));

        // then - verify the result
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(customers.size())));

    }
    
    @Test
    public void getCustomerByIdTest() throws Exception{
        // given - Data
        int customerId = 107;
        Customer customer = Customer.builder()
        		.customerId(107)
        		.customerName("Prince Kumar")
        		.customerEmail("prince@gmail.com")
        		.customerNumber("9131169055")
        		.title("Mr")
        		.dept("HR").build();
        given(customerService.getCustomerById(customerId)).willReturn(Optional.of(customer));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/getCustomer/{id}", customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.customerId",
                        is(customer.getCustomerId())))
                .andExpect(jsonPath("$.customerName",
                        is(customer.getCustomerName())))
                .andExpect(jsonPath("$.title",
                        is(customer.getTitle())))
                .andExpect(jsonPath("$.customerNumber",
                        is(customer.getCustomerNumber())))
                .andExpect(jsonPath("$.dept",
                        is(customer.getDept())))
                .andExpect(jsonPath("$.customerEmail",
                        is(customer.getCustomerEmail())));

    }

    @Test
    public void getCustomerByIdTest1() throws Exception{
        // given - data
    	int customerId = 10345;
        Customer customer = Customer.builder()
        		.customerId(107)
        		.customerName("Prince Kumar")
        		.customerEmail("prince@gmail.com")
        		.customerNumber("9131169055")
        		.title("Mr")
        		.dept("HR").build();
        given(customerService.getCustomerById(customerId)).willReturn(Optional.empty());

        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(get("/customer/getCustomer/{id}", customerId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
               

    }
    
    @Test
    public void updateTest() throws Exception{
        // given - mock data for test
        int customerId = 106;// common for update employee
        Customer saveCustomer = Customer.builder()
        		.customerId(106)
        		.customerName("Prince Kumar")
        		.customerEmail("prince@gmail.com")
        		.customerNumber("9131169055")
        		.title("Mr")
        		.dept("HR").build();

        Customer updateCustomer = Customer.builder()
        		.customerId(106)
        		.customerName("Ram Kumar")
        		.customerEmail("ram@gmail.com")
        		.customerNumber("8877684747")
        		.title("Mr")
        		.dept("HR").build();
        given(customerService.getCustomerById(customerId)).willReturn(Optional.of(saveCustomer));
        given(customerService.updateCustomer(any(Customer.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(put("/customer/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(updateCustomer)));


        // then - verify the result
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.customerId",
                        is(updateCustomer.getCustomerId())))
                .andExpect(jsonPath("$.customerName",
                        is(updateCustomer.getCustomerName())))
                .andExpect(jsonPath("$.title",
                        is(updateCustomer.getTitle())))
                .andExpect(jsonPath("$.customerNumber",
                        is(updateCustomer.getCustomerNumber())))
                .andExpect(jsonPath("$.dept",
                        is(updateCustomer.getDept())))
                .andExpect(jsonPath("$.customerEmail",
                        is(updateCustomer.getCustomerEmail())));
    }
    
    @Test
    public void updateTest1() throws Exception{
        // given - mock data for test
        int customerId = 106;// common for update employee

        Customer updateCustomer = Customer.builder()
        		.customerId(1011)
        		.customerName("Ram Kumar")
        		.customerEmail("ram@gmail.com")
        		.customerNumber("8877684747")
        		.title("Mr")
        		.dept("HR").build();
        
        given(customerService.getCustomerById(customerId)).willReturn(Optional.empty());
        given(customerService.updateCustomer(any(Customer.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(put("/customer/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(updateCustomer)));


        // then - verify the result
        response.andExpect(status().isOk())
        .andDo(print());
    }


    @Test
    public void deleteTest() throws Exception{
        // given - data
        int customerId = 103;
        willDoNothing().given(customerService).deleteCustomer(customerId);

        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(delete("/customer/delete/{id}", customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
   
    

}