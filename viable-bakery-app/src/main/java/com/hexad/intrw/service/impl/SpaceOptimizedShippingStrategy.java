package com.hexad.intrw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.java.Log;

import com.hexad.intrw.exception.BakeryException;
import com.hexad.intrw.model.BakeryProduct;
import com.hexad.intrw.model.Pack;
import com.hexad.intrw.model.Product;
import com.hexad.intrw.service.IShippingStrategy;

@Log
public class SpaceOptimizedShippingStrategy implements IShippingStrategy{

	@Override
	public void ship(int orderedQuantity, Product orderedProduct) {
		BakeryProduct product = (BakeryProduct)orderedProduct;
		log.info("Shipping " + product.getName() +" with quantity "+ orderedQuantity);
		
		findOptimizedBreakup(orderedQuantity, product);
		
	}

	private void findOptimizedBreakup(int orderedQuantity, BakeryProduct orderedProduct) {
		Map<Pack, Integer> finalShipping = new HashMap<Pack, Integer>();
 		ArrayList<Pack> sortedPacks = new ArrayList<Pack>(orderedProduct.getPacks());
		
		int remainder = orderedQuantity;
		Pack lastPushedPack = null;
		for(int i=0; i<sortedPacks.size(); i++){
			Pack pack = sortedPacks.get(i);
			int eachPackQuantity = pack.getQuantity();
			if(eachPackQuantity < remainder){
				remainder = eachPackQuantity%remainder;
				lastPushedPack = pack;
				finalShipping.put(pack, Integer.valueOf(eachPackQuantity/remainder));
			}
			
			if(remainder == 0){
				break;
			}
			
			if(remainder !=0 && pack.equals(orderedProduct.getSmallestPack())){
				if(finalShipping.isEmpty()){
					throw new BakeryException("Cannot Fulfill the order.");
				}
				else{
					i = i-1;
					int quantity = finalShipping.remove(lastPushedPack);
					remainder = lastPushedPack.getQuantity() * quantity + remainder;
				}
			}
			
			
		}
		
		
	}
	

}
