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
	public void loadTestMB11(){
		IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
		IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, "26 MB11");
		Map<Pack, Integer> finalShipping = bakeryService.order();
		for(Pack pack :finalShipping.keySet()){
			BigDecimal price = pack.getPrice();
			if(pack.getQuantity() == 8){
				assertEquals(new Integer(3), finalShipping.get(pack));
			}
			
		}
	}
}
