package com.hexad.intrw.service.impl;

import lombok.extern.java.Log;

import com.hexad.intrw.model.BakeryProduct;
import com.hexad.intrw.model.Product;
import com.hexad.intrw.service.IShippingStrategy;

@Log
public class SpaceOptimizedShippingStrategy implements IShippingStrategy{

	@Override
	public void ship(int orderedQuantity, Product orderedProduct) {
		BakeryProduct product = (BakeryProduct)orderedProduct;
		log.info("Shipping " + product.getName() +" with quantity "+ orderedQuantity);
		
		//Map<String> findOptimizedBreakup();
	}
	

}
