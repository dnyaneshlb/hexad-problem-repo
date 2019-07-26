package com.hexad.intrw.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import com.hexad.intrw.model.Product;
import com.hexad.intrw.service.IBakeryService;
import com.hexad.intrw.service.IShippingStrategy;
import com.hexad.intrw.util.CommonUtil;

@Getter
@Setter
@NoArgsConstructor
@Log
public class BakeryService implements IBakeryService{
	
	private IShippingStrategy shippingStrategy;
	private String order;

	private int orderedQuantity;
	private String orderedProductCode;
	private Product orderedProduct;
	
	public BakeryService(IShippingStrategy spaceOptimizedStrategy, String userOrder) {
		this.shippingStrategy = spaceOptimizedStrategy;
		this.order = userOrder;
	}
	
	@Override
	public void order() {
		log.info("ordering product");
		String[] split = this.order.split(" ");
		this.orderedQuantity = Integer.valueOf(split[0]);
		this.orderedProductCode = split[1];
		this.orderedProduct = CommonUtil.findOrderedProductByProductCode(this.orderedProductCode);
		
		this.shippingStrategy.ship(orderedQuantity,orderedProduct);
	}
}
