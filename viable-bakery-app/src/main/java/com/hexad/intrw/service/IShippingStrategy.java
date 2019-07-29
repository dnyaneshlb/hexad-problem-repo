package com.hexad.intrw.service;

import java.util.Map;

import com.hexad.intrw.model.Pack;
import com.hexad.intrw.model.Product;

public interface IShippingStrategy {
	public Map<Pack, Integer> ship(int orderedQuantity, Product orderedProduct);
}
