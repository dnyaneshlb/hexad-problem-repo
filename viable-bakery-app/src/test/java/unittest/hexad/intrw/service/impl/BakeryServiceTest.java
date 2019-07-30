package unittest.hexad.intrw.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.hexad.intrw.model.Pack;
import com.hexad.intrw.service.IBakeryService;
import com.hexad.intrw.service.IShippingStrategy;
import com.hexad.intrw.service.impl.BakeryService;
import com.hexad.intrw.service.impl.SpaceOptimizedShippingStrategy;
import com.hexad.intrw.util.CommonUtil;

public class BakeryServiceTest {
	
	@Before
	public void init(){
		CommonUtil.bootstrapInitData();
	}
	
	@Test
	public void testMB11(){
		IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
		IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, "26 MB11");
		Map<Pack, Integer> finalShipping = bakeryService.order();
		BigDecimal total = new BigDecimal(0);
		for(Pack pack :finalShipping.keySet()){
			BigDecimal price = pack.getPrice();
			price = price.setScale(2, BigDecimal.ROUND_CEILING);
			total = total.add(pack.getPrice().multiply(new BigDecimal(finalShipping.get(pack))));
			if(pack.getQuantity() == 8){
				assertEquals(new Integer(3), finalShipping.get(pack));
			}
			else if(pack.getQuantity() == 5){
				assertEquals(new Integer(3), finalShipping.get(pack));
			}
			else if(pack.getQuantity() == 3){
				assertEquals(new Integer(3), finalShipping.get(pack));
			}
		}
		
		BigDecimal expected = new BigDecimal(84.92);
		expected = expected.setScale(2, BigDecimal.ROUND_DOWN);
		total = total.setScale(2,BigDecimal.ROUND_CEILING);
		assertEquals(expected, total);
	}
	
	@Test
	public void testCF(){
		IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
		IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, "24 CF");
		Map<Pack, Integer> finalShipping = bakeryService.order();
		BigDecimal total = new BigDecimal(0);
		for(Pack pack :finalShipping.keySet()){
			BigDecimal price = pack.getPrice();
			price = price.setScale(2, BigDecimal.ROUND_CEILING);
			total = total.add(pack.getPrice().multiply(new BigDecimal(finalShipping.get(pack))));
			if(pack.getQuantity() == 3){
				assertEquals(new Integer(2), finalShipping.get(pack));
			}
			else if(pack.getQuantity() == 5){
				assertEquals(new Integer(0), finalShipping.get(pack));
			}
			else if(pack.getQuantity() == 9){
				assertEquals(new Integer(2), finalShipping.get(pack));
			}
		}
		
		BigDecimal expected = new BigDecimal(45.88);
		expected = expected.setScale(2, BigDecimal.ROUND_DOWN);
		total = total.setScale(2,BigDecimal.ROUND_CEILING);
		assertEquals(expected, total);
	}
	
	@Test
	public void testVS5(){
		IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
		IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, "14 VS5");
		Map<Pack, Integer> finalShipping = bakeryService.order();
		BigDecimal total = new BigDecimal(0);
		for(Pack pack :finalShipping.keySet()){
			BigDecimal price = pack.getPrice();
			price = price.setScale(2, BigDecimal.ROUND_CEILING);
			total = total.add(pack.getPrice().multiply(new BigDecimal(finalShipping.get(pack))));
			if(pack.getQuantity() == 5){
				assertEquals(new Integer(1), finalShipping.get(pack));
			}
			else if(pack.getQuantity() == 3){
				assertEquals(new Integer(3), finalShipping.get(pack));
			}
		}
		
		BigDecimal expected = new BigDecimal(29.96);
		expected = expected.setScale(2, BigDecimal.ROUND_DOWN);
		total = total.setScale(2,BigDecimal.ROUND_CEILING);
		assertEquals(expected, total);
	}
}
