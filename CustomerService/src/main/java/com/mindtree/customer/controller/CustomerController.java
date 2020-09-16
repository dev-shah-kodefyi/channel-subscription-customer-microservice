package com.mindtree.customer.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.customer.dto.CustomerDto;
import com.mindtree.customer.exception.ServiceException;
import com.mindtree.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) throws ServiceException
	{
		CustomerDto customerAdded = customerService.insertCustomer(customerDto);
		return new ResponseEntity<CustomerDto>(customerAdded, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{subscriberId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable String subscriberId) throws ServiceException
	{
		CustomerDto customerRetrived = customerService.retriveCustomer(subscriberId);
		return new ResponseEntity<CustomerDto>(customerRetrived, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<Set<CustomerDto>> getAllCustomers()
	{
		Set<CustomerDto> customerSet = customerService.retriveAllCustomers();
		return new ResponseEntity<Set<CustomerDto>>(customerSet, HttpStatus.OK);
	}
}
