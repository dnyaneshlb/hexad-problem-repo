package com.hexad.intrw.model;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"name", "packs"},callSuper=false)
public class BakeryProduct extends Product{
	
	@NonNull
	private String code;
	
	@NonNull
	private String name;
	
	@NonNull
	private Set<Pack> packs;
	
	private Pack smallestPack;
	
	public BakeryProduct(String name, String code, Set<Pack> packs){
		this.name = name;
		this.code = code;
		this.packs = packs;
	}
	
	
}
