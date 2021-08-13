package com.dairy.managment.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.managment.app.entity.Advance;
import com.dairy.managment.app.entity.Customer;
import com.dairy.managment.app.entity.repository.AdvanceRepository;
import com.dairy.managment.app.entity.repository.CustomerRepository;
import com.dairy.managment.app.enums.EntryType;
import com.dairy.managment.app.rest.RestAdvance;

@Service
public class AdvanceService {

	@Autowired
	AdvanceRepository advanceRepository;
	@Autowired
	CustomerRepository customerRepostiry;

	@Transactional
	public Advance addAdance(RestAdvance inputAdvanceData) {

		Customer customerDetails = customerRepostiry.getById(inputAdvanceData.getCustomer_id());

		Advance advance = new Advance();
		BeanUtils.copyProperties(inputAdvanceData, advance);

		double newAdvanceBalance;

		if (inputAdvanceData.getEntry_type() == EntryType.DEBIT) {
			if (customerDetails.getAdvance_balance() >= advance.getAmount()) {
				newAdvanceBalance = customerDetails.getAdvance_balance() - advance.getAmount();
			} else
				throw new IllegalStateException(
						"Maximum advance can be deducted is : " + customerDetails.getAdvance_balance());
		} else {
			newAdvanceBalance = customerDetails.getAdvance_balance() + advance.getAmount();
		}

		advance.setClosing_balance(newAdvanceBalance);
		customerDetails.setAdvance_balance(newAdvanceBalance);

		customerRepostiry.save(customerDetails);

		return advanceRepository.save(advance);
	}

}
