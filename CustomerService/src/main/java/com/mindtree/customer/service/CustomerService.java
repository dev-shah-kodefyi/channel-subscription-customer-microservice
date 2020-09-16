package com.mindtree.customer.service;

import java.util.Set;

import com.mindtree.customer.dto.CustomerDto;
import com.mindtree.customer.exception.ServiceException;

public interface CustomerService {

	CustomerDto insertCustomer(CustomerDto customerDto) throws ServiceException;

	CustomerDto retriveCustomer(String subscriberId) throws ServiceException;

	Set<CustomerDto> retriveAllCustomers();

}
