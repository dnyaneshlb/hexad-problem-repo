package unittest.hexad.intrw.service.impl;

import org.junit.Before;
import org.junit.Test;

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
		String code = "MB11";
		execute(code);
	}
	
	@Test
	public void loadTestCF(){
		String code = "CF";
		execute(code);
	}
	
	@Test
	public void loadTestVS5(){
		String code = "VS5";
		execute(code);
	}
	
	private void execute(String code){
		IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
		String space = " ";
		for(int i=0; i<100; i++){
			String order = i + space + code;
			IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, order);
			bakeryService.order();
			System.out.println();
		}
	}
}
