package com.hexad.intrw.service.impl;

import java.util.Map;

import com.hexad.intrw.model.Pack;
import com.hexad.intrw.model.Product;
import com.hexad.intrw.service.IShippingStrategy;

public class CostOptimizedShippingStrategy implements IShippingStrategy{
	/*
	 * to be implemented for a case when a bakery needs to ship products based on a cost.
	 * 
	 */
	
	@Override
	public Map<Pack, Integer> ship(int orderedQuantity, Product orderedProduct) {
		// TODO Auto-generated method stub
		return null;
	}
}
