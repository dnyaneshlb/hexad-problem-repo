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
	 */
	
	@Before
	public void init(){
		CommonUtil.bootstrapInitData();
	}
	
	@Test
	public void test_cannot_fulfill_order(){
		IShippingStrategy strategy = new SpaceOptimizedShippingStrategy();
		Map<Pack, Integer> result = strategy.ship(1, CommonUtil.findOrderedProductByProductCode("MB11"));
		assertEquals(null, result);
	}
	
	
	/**
	 * Positive test cases
	 */
	@Test
	public void test_fulfill_order(){
		IShippingStrategy strategy = new SpaceOptimizedShippingStrategy();
		Map<Pack, Integer> result = strategy.ship(10, CommonUtil.findOrderedProductByProductCode("MB11"));
		
	}
	
}
