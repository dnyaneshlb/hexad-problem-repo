package com.hexad.intrw.service.impl;

import java.util.Arrays;

import lombok.extern.java.Log;

import com.hexad.intrw.model.ProductCode;
import com.hexad.intrw.service.IOrderValidationService;
import com.hexad.intrw.util.CommonUtil;

@Log
public class OrderValidationService implements IOrderValidationService{

	@Override
	public boolean isValidOrder(String userOrder) {
		log.info("Starting order valiation : " + userOrder );
		
		//check null or empty
		if(CommonUtil.isNullOrEmpty(userOrder)) return false;
		
		//trim input
		userOrder = userOrder.trim();
		
		//check middle spaces. There should be exactly 2 elements, space seperated
		String[] splits = userOrder.split(" ");
		if(splits.length != 2) return false;
		
		
		//Check if first element is parsable integer
		try {
			Integer.valueOf(splits[0]);
		} catch (NumberFormatException e) {
			return false;
		}
		
		int orderQuantity = Integer.valueOf(splits[0]);
		String code = splits[1];
		
		//check order quantity - zero and negative number
		if(orderQuantity<1) return false;

		//check check order quantity - too huge..out of integer limit
		if(orderQuantity > Integer.MAX_VALUE) return false;
			
		
		//check second element belongs to product code
		if(!Arrays.asList(ProductCode.values()).contains(ProductCode.valueOf(code))) return false;
		
		log.info("Order validation completed sucessfully.");
		return true;
	}
	
}
