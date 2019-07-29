package com.hexad.intrw.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hexad.intrw.model.BakeryProduct;
import com.hexad.intrw.model.Pack;
import com.hexad.intrw.model.Product;
import com.hexad.intrw.model.ProductCode;

public class CommonUtil {
	
	public static void bootstrapInitData(){
		AppData.vegemiteScroll = getVegemiteScroll();
		AppData.blueberryMuffin = getBlueberryMuffin();
		AppData.croissant = getCroissant();
	}

	private static Product getCroissant() {
		Pack pack1 = new Pack(3,new BigDecimal(5.95));
		Pack pack2 = new Pack(5,new BigDecimal(9.95));
		Pack pack3 = new Pack(9,new BigDecimal(16.99));
		List<Pack> packs = new ArrayList<Pack>();
		packs.add(pack3);
		packs.add(pack2);
		packs.add(pack1);
		BakeryProduct croissant = new BakeryProduct("Croissant",ProductCode.CF, packs);
		croissant.setSmallestPack(pack1);
		return croissant;
	}

	private static Product getBlueberryMuffin() {
		Pack pack1 = new Pack(2,new BigDecimal(9.95));
		Pack pack2 = new Pack(5,new BigDecimal(16.95));
		Pack pack3 = new Pack(8,new BigDecimal(24.99));
		List<Pack> packs = new ArrayList<Pack>();
		packs.add(pack3);
		packs.add(pack2);
		packs.add(pack1);
		BakeryProduct blueberryMuffin = new BakeryProduct("Blueberry Muffin", ProductCode.MB11, packs);
		blueberryMuffin.setSmallestPack(pack1);
		return blueberryMuffin;
	}

	private static Product getVegemiteScroll() {
		Pack pack1 = new Pack(3,new BigDecimal(6.99));
		Pack pack2 = new Pack(5,new BigDecimal(8.99));
		List<Pack> packs = new ArrayList<Pack>();
		packs.add(pack2);
		packs.add(pack1);
		BakeryProduct vegemiteScroll = new BakeryProduct("Vegemite Scroll", ProductCode.VS5, packs);
		vegemiteScroll.setSmallestPack(pack1);
		return vegemiteScroll;
	}

	public static Product findOrderedProductByProductCode(String orderedProductCode) {
		Product product = null ;
		switch(orderedProductCode){
			case "VS5" : product = AppData.vegemiteScroll;break;
			case "MB11" : product = AppData.blueberryMuffin;break;
			case "CF" : product = AppData.croissant;break;
			//there is no need to add default case since we are very much sure that 
			//our order validation service has taken care of rejecting orders with wrong product code.
		}
		return product;
	}
	
	
	public static boolean isNullOrEmpty(String s){
		if(s == null) return true;
		if(s.trim().length() == 0) return true;
		return false;
	}
	
	
}
