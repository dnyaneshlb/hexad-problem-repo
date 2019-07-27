package com.hexad.intrw.service.impl;

import com.hexad.intrw.service.IOrderValidationService;

public class OrderValidationService implements IOrderValidationService{

	@Override
	public boolean validate(String userOrder) {
		//trim input
		
		//check middle spaces. There should be exactly 2 elements, space seperated
		
		//check zero and negative number

		//check order too huge..out of integer limit
		
		//check product code
		
		return true;
	}
	
}
