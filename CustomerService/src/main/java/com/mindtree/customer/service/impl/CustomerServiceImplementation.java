package com.mindtree.customer.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindtree.customer.dto.CustomerDto;
import com.mindtree.customer.entity.Customer;
import com.mindtree.customer.exception.InvalidSubsciberIdException;
import com.mindtree.customer.exception.ServiceException;
import com.mindtree.customer.exception.SubscriberAlreadyPresentException;
import com.mindtree.customer.exception.SubscriberNotFoundException;
import com.mindtree.customer.repository.CustomerRepository;
import com.mindtree.customer.service.CustomerService;

@Service
public class CustomerServiceImplementation implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	//Insert new Subscriber
	@Override
	public CustomerDto insertCustomer(CustomerDto customerDto) throws ServiceException
	{
		Customer customerInserted = null;
		try
		{
			if(!customerRepo.existsById(customerDto.getSubscriberId()))
			{
				try
				{
					if(customerDto.getSubscriberId().length() == 10)
					{
						Customer customer = convertDtoToEntity(customerDto);
						customerInserted = customerRepo.save(customer);
					}
					else
					{
						throw new InvalidSubsciberIdException("Subscriber Id Invalid!");
					}
				}
				catch(InvalidSubsciberIdException e)
				{
					throw new ServiceException(e.getMessage());
				}
			}
			else
			{
				throw new SubscriberAlreadyPresentException("Subcriber Id Already Present!");
			}
		}
		catch(SubscriberAlreadyPresentException e)
		{
			throw new ServiceException(e.getMessage());
		}
		
		return convertEntityToDto(customerInserted);
	}

	//To Retrieve Subscriber from Database
	@Override
	public CustomerDto retriveCustomer(String subscriberId) throws ServiceException
	{
		Customer customerRetrived = null;
		
		try
		{
			if(customerRepo.existsById(subscriberId))
			{
				customerRetrived = customerRepo.findById(subscriberId).get();
			}
			else
			{
				throw new SubscriberNotFoundException("Subscriber Not Found! Check Id!");
			}
		}
		catch(SubscriberNotFoundException e)
		{
			throw new ServiceException(e.getMessage());
		}
		
		return convertEntityToDto(customerRetrived);
	}

	//To Retrieve all the Customers From the Database
	@Override
	@Cacheable(value = "customers")
	public Set<CustomerDto> retriveAllCustomers()
	{
		List<Customer> customerList = customerRepo.findAll();
		
		Set<CustomerDto> customerDtoSet = customerList
												.stream()
												.map(customer -> convertEntityToDto(customer))
												.collect(Collectors.toSet());
		return customerDtoSet;
	}
	
	//Converting Entity To Dto
	private CustomerDto convertEntityToDto(Customer customer)
	{
		return modelMapper.map(customer, CustomerDto.class);
	}
	
	//Converting Dto To Entity
	private Customer convertDtoToEntity(CustomerDto customerDto)
	{
		return modelMapper.map(customerDto, Customer.class);
	}

	

}
