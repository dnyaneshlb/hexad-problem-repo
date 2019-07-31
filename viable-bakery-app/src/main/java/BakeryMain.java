import java.math.BigDecimal;
import java.util.Map;

import lombok.extern.java.Log;

import com.hexad.intrw.exception.BakeryException;
import com.hexad.intrw.exception.InvalidOrderException;
import com.hexad.intrw.model.Pack;
import com.hexad.intrw.service.IBakeryService;
import com.hexad.intrw.service.IOrderValidationService;
import com.hexad.intrw.service.IShippingStrategy;
import com.hexad.intrw.service.impl.BakeryService;
import com.hexad.intrw.service.impl.OrderValidationService;
import com.hexad.intrw.service.impl.SpaceOptimizedShippingStrategy;
import com.hexad.intrw.util.CommonUtil;

/*
 * This entry point to our bakery app
 */
@Log
public class BakeryMain {
	public static void main(String[] args) {
		//load the data structures
		CommonUtil.bootstrapInitData();
		IOrderValidationService validationService = new OrderValidationService();
		String[] userOrders = getUserInput(args);
		for(String order : userOrders){
			order(validationService, order.trim());
		}
	}

	/**
	 * @param validationService
	 */
	private static void order(IOrderValidationService validationService, String userOrder) {
		log.info("order received : "+ userOrder);
		try {
			if(validationService.isValidOrder(userOrder)){
				IShippingStrategy spaceOptimizedStrategy = new SpaceOptimizedShippingStrategy();
				IBakeryService bakeryService = new BakeryService(spaceOptimizedStrategy, userOrder);
				log.info("ordering "+ userOrder);
				Map<Pack, Integer> result = bakeryService.order();
				
				
				if(result !=  null && !result.isEmpty()){
					BigDecimal total = new BigDecimal(0);
					for(Pack pack :result.keySet()){
						BigDecimal price = pack.getPrice();
						price = price.setScale(2, BigDecimal.ROUND_CEILING);
						System.out.println(pack.getQuantity() + " * " + result.get(pack) + " " + price);
						total = total.add(pack.getPrice().multiply(new BigDecimal(result.get(pack))));
					}
					System.out.println("Total Price for "+  userOrder + " is " + total.setScale(2,BigDecimal.ROUND_CEILING));
				}
				else{
					throw new BakeryException(userOrder + " cannot be fulfilled. Please either increase or decrease quantity.");
				}
				
			}
			else {
				log.severe("Validation failed for order : "+ userOrder);
				throw new InvalidOrderException(userOrder + " is invalid order. Correct way to place order is 'Quantity ProductCode'. e.g. 10 MB11");
			}
		} 
		catch ( InvalidOrderException e) {
			System.out.println(e.getMessage());
		}
		catch ( BakeryException e) {
			System.out.println(e.getMessage());
		}
		catch ( Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static String[] getUserInput(String[] args) {
		String[]  orders = null;
		if(args != null && args.length == 1){
			orders = args[0].split("&");
		}
		return orders;
	}
}
