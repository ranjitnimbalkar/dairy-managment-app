package com.dairy.managment.app.service;

import javax.naming.OperationNotSupportedException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.managment.app.entity.Customer;
import com.dairy.managment.app.entity.Food;
import com.dairy.managment.app.entity.repository.CustomerRepository;
import com.dairy.managment.app.entity.repository.FoodRepository;
import com.dairy.managment.app.enums.EntryType;
import com.dairy.managment.app.rest.RestFood;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public Food addFood(RestFood foodEntry) throws OperationNotSupportedException {
		
		Food food = new Food();		
		BeanUtils.copyProperties(foodEntry, food);	
		
		Customer customer = customerRepository.getById(foodEntry.getCustomer_id());
		
		double newBalance;
		
		if(foodEntry.getEntry_type() == EntryType.DEBIT) {
			if(customer.getFood_balance() >= foodEntry.getAmount())
				newBalance = customer.getFood_balance() - foodEntry.getAmount();
			else
				throw new OperationNotSupportedException("Current food balance is : "+customer.getFood_balance());
		}else {
			newBalance = customer.getFood_balance() + foodEntry.getAmount();
		}
		
		food.setClosing_balance(newBalance);
		customer.setFood_balance(newBalance);
		
		customerRepository.save(customer);
		
		return foodRepository.save(food);
	}

}
