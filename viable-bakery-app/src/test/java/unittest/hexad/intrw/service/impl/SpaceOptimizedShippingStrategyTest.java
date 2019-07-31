package unittest.hexad.intrw.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.hexad.intrw.model.Pack;
import com.hexad.intrw.service.IShippingStrategy;
import com.hexad.intrw.service.impl.SpaceOptimizedShippingStrategy;
import com.hexad.intrw.util.CommonUtil;


public class SpaceOptimizedShippingStrategyTest {

	/**
	 * Functional Limitation Test cases
	 * This test case tests if given quantity for given product code can be fulfilled.
	 */
	
	@Before
	public void init(){
		CommonUtil.bootstrapInitData();
	}
	
	@Test
	public void test_cannot_fulfill_order_1(){
		IShippingStrategy strategy = new SpaceOptimizedShippingStrategy();
		Map<Pack, Integer> result = strategy.ship(1, CommonUtil.findOrderedProductByProductCode("MB11"));
		assertEquals(null, result);
	}
	
	
	@Test
	public void test_cannot_fulfill_order_2(){
		IShippingStrategy strategy = new SpaceOptimizedShippingStrategy();
		Map<Pack, Integer> result = strategy.ship(7, CommonUtil.findOrderedProductByProductCode("CF"));
		assertEquals(null, result);
	}
	
}
