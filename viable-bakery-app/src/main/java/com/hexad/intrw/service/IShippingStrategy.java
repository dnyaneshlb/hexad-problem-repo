package com.hexad.intrw.service;

import com.hexad.intrw.model.Product;

public interface IShippingStrategy {
	public void ship(int orderedQuantity, Product orderedProduct);
}
