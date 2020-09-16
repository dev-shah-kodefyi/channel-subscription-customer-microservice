package com.mindtree.subscription.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mindtree.subscription.dto.CustomerDto;

@FeignClient(name = "customer-service", url = "http://localhost:9091/customer")
public interface CustomerFeign {

	@GetMapping("/getCustomer/{subscriberId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable String subscriberId) throws ServiceException;
}
