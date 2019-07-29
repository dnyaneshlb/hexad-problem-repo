package com.hexad.intrw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lombok.extern.java.Log;

import com.hexad.intrw.model.BakeryProduct;
import com.hexad.intrw.model.Pack;
import com.hexad.intrw.model.Product;
import com.hexad.intrw.service.IShippingStrategy;

@Log
public class SpaceOptimizedShippingStrategy implements IShippingStrategy{

	@Override
	public Map<Pack, Integer> ship(int orderedQuantity, Product orderedProduct) {
		BakeryProduct product = (BakeryProduct)orderedProduct;
		log.info("Shipping " + product.getName() +" with quantity "+ orderedQuantity);
		
		Map<Pack, Integer> finalShipping = findOptimizedBreakup(orderedQuantity, product);
		return finalShipping;
	}

	private Map<Pack, Integer> findOptimizedBreakup(int orderedQuantity, BakeryProduct orderedProduct) {
		Map<Pack, Integer> finalShipping = new HashMap<Pack, Integer>();
		LinkedList<Pack> stack = new LinkedList<Pack>();
		Pack smallestPack = orderedProduct.getSmallestPack();
 		ArrayList<Pack> sortedPacks = (ArrayList<Pack>) orderedProduct.getPacks();
		int remainder = orderedQuantity;
		for(int i=0; i<sortedPacks.size(); i++){
			Pack pack = sortedPacks.get(i);
			int eachPackQuantity = pack.getQuantity();
			if(remainder >= eachPackQuantity){
				if(((remainder%eachPackQuantity) !=0) 
						&& ((remainder%eachPackQuantity) < smallestPack.getQuantity()) 
						&& pack.equals(smallestPack)){
					//System.out.println("xx");
				}
				else{
					stack.push(pack);
					finalShipping.put(pack, Integer.valueOf(remainder/eachPackQuantity));
					remainder = remainder%eachPackQuantity;
				}
			}
			
			if(remainder == 0){
				break;
			}
			
			if(remainder !=0 && pack.equals(smallestPack)){
				if(finalShipping.isEmpty()){
					//throw new BakeryException("Cannot Fulfill the order.");
					log.info("Cannot fulfill order quantity :" + orderedQuantity);
					return null;
				}
				else{
					Pack lastPushedPack = stack.peekFirst();
					i = sortedPacks.indexOf(lastPushedPack);//this will be i++ in for loop, so we will get right index
					if(finalShipping.containsKey(lastPushedPack)){
						Integer q = finalShipping.get(lastPushedPack);
						if(q == 1){
							stack.pop();
							finalShipping.remove(lastPushedPack);
						}
						else{
							finalShipping.put(lastPushedPack, --q);
						}
						remainder = lastPushedPack.getQuantity() + remainder;
					}
				}
			}
		}
		
		
		return finalShipping;
	}

	

}
