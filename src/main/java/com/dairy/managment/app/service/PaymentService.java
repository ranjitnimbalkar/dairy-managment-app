package com.dairy.managment.app.service;

import java.util.Date;

import javax.naming.OperationNotSupportedException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.managment.app.entity.Advance;
import com.dairy.managment.app.entity.Customer;
import com.dairy.managment.app.entity.Payment;
import com.dairy.managment.app.entity.repository.AdvanceRepository;
import com.dairy.managment.app.entity.repository.CustomerRepository;
import com.dairy.managment.app.entity.repository.PaymentRepository;
import com.dairy.managment.app.enums.EntryType;
import com.dairy.managment.app.rest.RestPayment;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AdvanceRepository advanceRepository;

	@Transactional(rollbackOn = {OperationNotSupportedException.class} )
	public Payment addPaymentEntry(RestPayment paymentData) throws OperationNotSupportedException {

		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentData, payment);

		payment = paymentRepository.save(payment);

		Customer customer = customerRepository.getById(payment.getCustomerId());

		insertAdvaceDeduction(payment, customer);
		insertFoodDeduction(payment, customer);

		customer.setAdvance_balance(customer.getAdvance_balance() - payment.getAdvance_deduction());
		customer.setFood_balance(customer.getFood_balance() - payment.getFood_deduction());
		customerRepository.save(customer);

		return payment;
	}

	private void insertAdvaceDeduction(Payment payment, Customer customer) throws OperationNotSupportedException {
		 
		if(customer.getAdvance_balance() >= payment.getAdvance_deduction()) {
			Advance advance = new Advance();
			advance.setDate(new Date());
			advance.setAmount(payment.getAdvance_deduction());
			advance.setEntry_type(EntryType.DEBIT);
			advance.setClosing_balance(customer.getAdvance_balance() - payment.getAdvance_deduction());
			advance.setComment("Deduction from payment : " + payment.getId());
			advance.setCustomer_id(customer.getId());
			advance.setPayment_id(payment.getId());
	
			advanceRepository.save(advance);
		}else {
			throw new OperationNotSupportedException("Maximum Advance deduction can be : "+ customer.getAdvance_balance());
		}
	}
	
	private void insertFoodDeduction(Payment payment, Customer customer) throws OperationNotSupportedException {
		 
		if(customer.getFood_balance() >= payment.getFood_deduction()) {
		    
		}else {
			throw new OperationNotSupportedException("Maximum Food deduction can be : "+ customer.getFood_balance());
		}
	}

}
