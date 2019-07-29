package com.hexad.intrw.service;

import java.util.Map;

import com.hexad.intrw.model.Pack;

public interface IBakeryService {
	
	public Map<Pack, Integer> order();
}
