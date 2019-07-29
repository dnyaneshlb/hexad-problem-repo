package loadtest.hexad.intrw;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.hexad.intrw.model.Pack;
import com.hexad.intrw.service.IBakeryService;
import com.hexad.intrw.service.IShippingStrategy;
import com.hexad.intrw.service.impl.BakeryService;
import com.hexad.intrw.service.impl.SpaceOptimizedShippingStrategy;
import com.hexad.intrw.util.CommonUtil;

/**
 * This test runs the app for all products with quantity ranging from 0 to 100.
 * Aim is to find a order quantity 
 * 		which cannot be fulfilled OR
 * 		which breaks the app 
 * 
 * For product and order quantity combination this test should print failures.
 * 
 * @author yr9kvt
 *
 */
public class BakeryServiceLoadTest {

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
			Map<Pack, Integer> finalShipping = bakeryService.order();
			if(finalShipping != null && !finalShipping.isEmpty()){
				int total = 0;
				for(Pack pack :finalShipping.keySet()){
					total = total + (pack.getQuantity() * finalShipping.get(pack));
				}
				
				if(total != i){
					System.out.println("\n ************  Failure for "+ i + "************ \n");
				}
				/*else{
					System.out.println("successful for " + i);
				}*/
			}
		}
	}
}
