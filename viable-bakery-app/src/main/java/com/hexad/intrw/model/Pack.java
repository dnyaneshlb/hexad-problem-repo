package com.hexad.intrw.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pack {
	private int quantity;
	
	private BigDecimal price; 
	
	//TODO: I should not be able to create pack with same quantity but diffrent price in same system.
}
