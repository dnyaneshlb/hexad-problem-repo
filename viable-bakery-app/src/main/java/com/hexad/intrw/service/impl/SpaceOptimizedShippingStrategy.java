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
		Pack smallestPack = orderedProduct.getSmallestPack();
 		ArrayList<Pack> packs = new ArrayList<Pack>(orderedProduct.getPacks());
 		ArrayList<Pack> sortedPacks = getDescending(packs);
		int remainder = orderedQuantity;
		Pack lastPushedPack = null;
		for(int i=0; i<sortedPacks.size(); i++){
			Pack pack = sortedPacks.get(i);
			int eachPackQuantity = pack.getQuantity();
			if(eachPackQuantity <= remainder){
				if(((remainder%eachPackQuantity) !=0) 
						&& ((remainder%eachPackQuantity) < smallestPack.getQuantity()) 
						&& remainder/eachPackQuantity == 1){
					System.out.println("xx");
				}
				else{
					lastPushedPack = pack;
					finalShipping.put(pack, Integer.valueOf(remainder/eachPackQuantity));
					remainder = remainder%eachPackQuantity;
				}
			}
			
			if(remainder == 0){
				break;
			}
			
			if(remainder !=0 && pack.equals(smallestPack)){
				if(finalShipping.isEmpty()){
					throw new BakeryException("Cannot Fulfill the order.");
				}
				else{
					i = sortedPacks.indexOf(lastPushedPack);//this will be i++ in for loop, so we will get right index
					if(finalShipping.containsKey(lastPushedPack)){
						Integer q = finalShipping.get(lastPushedPack);
						if(q == 1){
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
		
		System.out.println(finalShipping);
		
		
	}

	private ArrayList<Pack> getDescending(ArrayList<Pack> packsss) {
		
		Pack pack1 = new Pack(2,9.95);
		Pack pack2 = new Pack(5,16.95);
		Pack pack3 = new Pack(8,24.99);
		ArrayList<Pack> packs = new ArrayList<Pack>();
		packs.add(0, pack3);
		packs.add(1, pack2);
		packs.add(2, pack1);
		return packs;
	}
	

}
