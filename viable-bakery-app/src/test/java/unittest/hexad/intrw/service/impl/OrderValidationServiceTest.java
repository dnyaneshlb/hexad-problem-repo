package unittest.hexad.intrw.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hexad.intrw.service.IOrderValidationService;
import com.hexad.intrw.service.impl.OrderValidationService;

public class OrderValidationServiceTest {
	
	/**
	 * Negative test cases.
	 */
	IOrderValidationService validationService;
	
	@Before
	public void setup(){
		validationService = new OrderValidationService();
	}
	
	@Test
	public void test_with_zero_quantity(){
		boolean result = validationService.isValidOrder("0 CF");
		assertEquals(false, result);
	}
	
	@Test
	public void test_with_positive_unacceptable_quantity(){
		//this should return false when ordered quantity is less than pack of least size
		boolean result = validationService.isValidOrder(Integer.MAX_VALUE+1 + " CF");
		assertEquals(false, result);
	}
	
	@Test
	public void test_with_negative_quantity(){
		//this should throw exception
		boolean result = validationService.isValidOrder("-10 CF");
		assertEquals(false, result);
	}
	
	@Test
	public void test_with_unavailable_product_code(){
		//this should return false as wrong code
		// also this should return possible products matches (just like git suggestions)
		boolean result = validationService.isValidOrder("10 CFQQ");
		assertEquals(false, result);
	}
	
	@Test
	public void test_with_invalid_input_string1(){
		//product quantity is correct but there isn't a space as depicted in problem statement
		boolean result = validationService.isValidOrder("10CF");
		assertEquals(false, result);
	}
	
	@Test
	public void test_with_invalid_input_string2(){
		//product quantity is correct but there isn't a space as depicted in problem statement
		boolean result = validationService.isValidOrder("10 CF 11");
		assertEquals(false, result);
	}
	
	
	//check null or empty
	@Test
	public void test_with_invalid_string(){
		boolean result = validationService.isValidOrder("   ");
		assertEquals(false, result);
	}
	
	
	
	/**
	 * Positive test cases.
	 */
	@Test
	public void test_with_single_correct_input_product(){
		//e.g. 10 VS5
		//should run successfully
		boolean result = validationService.isValidOrder("10 CF");
		assertEquals(true, result);
	}
	
	@Test
	public void test_with_single_correct_input_product2(){
		//e.g. 10 VS5
		//should run successfully
		boolean result = validationService.isValidOrder("10 VS5");
		assertEquals(true, result);
	}
}
