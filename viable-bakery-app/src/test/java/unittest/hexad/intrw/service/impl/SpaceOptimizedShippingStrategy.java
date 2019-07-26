package unittest.hexad.intrw.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hexad.intrw.service.IBakeryService;
import com.hexad.intrw.service.impl.BakeryService;


public class SpaceOptimizedShippingStrategy {

	/**
	 * Negative test cases.
	 */
	
	@Test
	public void test_with_zero_quantity(){
		//this should throw exception
	}
	
	@Test
	public void test_with_positive_unacceptable_quantity(){
		//this should throw exception when ordered quantity is less than pack of least size 
	}
	
	@Test
	public void test_with_negative_quantity(){
		//this should throw exception
	}
	
	@Test
	public void test_with_unavailable_product_code(){
		//this should throw exception of wrong code
		// also this should return possible products matches (just like git suggestions)
	}
	
	@Test
	public void test_with_invalid_input_string(){
		//product quantity is correct but there isn't a space as depicted in problem statement
	}
	
	
	/**
	 * Positive test cases.
	 */
	@Test
	public void test_with_single_correct_input_product(){
		//e.g. 10 VS5
		//should run successfully
		assertEquals(null, "10 VS5 $17.98 2 x 5 $8.99");
	}
	
	@Test
	public void test_with_two_correct_input_product(){
		//e.g. 10 VS5
		//should run successfully
		assertEquals(null, "10 VS5 $17.98 2 x 5 $8.99 14 MB11 $54.8 1 x 8 $24.95 3 x 2 $9.95");
	}
	
	
	@Test
	public void test_with_three_correct_input_product(){
		//e.g. 10 VS5
		//should run successfully
		assertEquals(null, "10 VS5 $17.98 2 x 5 $8.99 14 MB11 $54.8 1 x 8 $24.95 3 x 2 $9.95 13 CF $25.85 2 x 5 $9.95 1 x 3 $5.95");
	}
	
	
	/**
	 * Functional Limitation Test cases
	 */
	
	@Test
	public void test_with_limitation(){
		IBakeryService bakery = new BakeryService();
		bakery.order();
	}
}
