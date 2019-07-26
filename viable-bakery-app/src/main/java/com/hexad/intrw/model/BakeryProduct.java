package com.hexad.intrw.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={
		"name", "packs"
})
public class BakeryProduct extends Product{
	
	@NonNull
	private String code;
	
	@NonNull
	private String name;
	
	@NonNull
	private Set<Pack> packs;
}
