package com.hexad.intrw.util;

import java.util.HashSet;
import java.util.Set;

import com.hexad.intrw.model.BakeryProduct;
import com.hexad.intrw.model.Pack;
import com.hexad.intrw.model.Product;

public class CommonUtil {
	
	public static void bootstrapInitData(){
		AppData.vegemiteScroll = getVegemiteScroll();
		AppData.blueberryMuffin = getBlueberryMuffin();
		AppData.croissant = getCroissant();
	}

	private static Product getCroissant() {
		Pack pack1 = new Pack(3,5.95);
		Pack pack2 = new Pack(5,9.95);
		Pack pack3 = new Pack(9,16.99);
		Set<Pack> packs = new HashSet<Pack>();
		packs.add(pack1);
		packs.add(pack2);
		packs.add(pack3);
		Product croissant = new BakeryProduct("Croissant","CF", packs);
		return croissant;
	}

	private static Product getBlueberryMuffin() {
		Pack pack1 = new Pack(2,9.95);
		Pack pack2 = new Pack(5,16.95);
		Pack pack3 = new Pack(8,24.99);
		Set<Pack> packs = new HashSet<Pack>();
		packs.add(pack1);
		packs.add(pack2);
		Product blueberryMuffin = new BakeryProduct("Blueberry Muffin", "MB11", packs);
		return blueberryMuffin;
	}

	private static Product getVegemiteScroll() {
		Pack pack1 = new Pack(3,6.99);
		Pack pack2 = new Pack(5,8.99);
		Set<Pack> packs = new HashSet<Pack>();
		packs.add(pack1);
		packs.add(pack2);
		Product vegemiteScroll = new BakeryProduct("Vegemite Scroll", "VS5", packs);
		return vegemiteScroll;
	}

	public static Product findOrderedProductByProductCode(String orderedProductCode) {
		Product product = null ;
		switch(orderedProductCode){
			case "VS5" : product = AppData.vegemiteScroll;
			case "MB11" : product = AppData.blueberryMuffin;
			case "CF" : product = AppData.croissant;
			//there is no need to add default case since we are very much sure that 
			//our order validation service has taken care of rejecting orders with wrong product code.
		}
		return product;
	}
	
	
	
	
}