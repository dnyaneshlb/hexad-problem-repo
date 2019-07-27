import com.hexad.intrw.exception.InvalidOrderException;
import com.hexad.intrw.service.IBakeryService;
import com.hexad.intrw.service.IOrderValidationService;
import com.hexad.intrw.service.IShippingStrategy;
import com.hexad.intrw.service.impl.BakeryService;
import com.hexad.intrw.service.impl.OrderValidationService;
import com.hexad.intrw.service.impl.SpaceOptimizedShippingStrategy;
import com.hexad.intrw.util.CommonUtil;

/*
 * This entrypoint to app
 */
public class BakeryMain {
	public static void main(String[] args) {
		//load the data structures
		CommonUtil.bootstrapInitData();
		IOrderValidationService validationService = new OrderValidationService();
		String userOrder = "14 MB101";
		try {
			if(validationService.isValidOrder(userOrder)){
				IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
				IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, userOrder);
				bakeryService.order();
			}
			else {
				throw new InvalidOrderException(userOrder + " is invalid order. Correct way to place order is 'Quantity ProductCode'. e.g. 10 MB11");
			}
		} catch ( Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
