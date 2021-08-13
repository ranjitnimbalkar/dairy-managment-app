package com.dairy.managment.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.managment.app.entity.Address;
import com.dairy.managment.app.entity.Customer;
import com.dairy.managment.app.entity.repository.CustomerRepository;
import com.dairy.managment.app.rest.RestCustomer;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository custRepo;
	
	public Customer addCustomer(RestCustomer inpuCustomerDetails) {
		Customer cust = new Customer();
		BeanUtils.copyProperties(inpuCustomerDetails, cust);
		Address addr = new Address();
		BeanUtils.copyProperties(inpuCustomerDetails.getAddress(), addr);
		cust.setAddress(addr);
		System.out.println(cust);
		return custRepo.save(cust);
	}

}
