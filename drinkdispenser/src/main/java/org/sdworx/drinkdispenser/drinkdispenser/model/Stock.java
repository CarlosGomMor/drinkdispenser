package org.sdworx.drinkdispenser.drinkdispenser.model;

import java.util.HashMap;
import java.util.Map;

import org.sdworx.drinkdispenser.drinkdispenser.exception.SoldOutException;

public class Stock {
	
	private Integer ZERO = 0;
	
	Map<Drink, Integer> drinks = new HashMap<Drink, Integer>();
	
	public void add(Drink drink, Integer amount) {
		drinks.put(drink, amount);
	}

	public Stock() {
		super();
	}
	public Stock(Map<Drink, Integer> drinks) {
		super();
		this.drinks = drinks;
	}
	
	public Integer getStock (Drink drink) throws SoldOutException {
		Integer result = this.drinks.get(drink);
		
		if (result == null || result<ZERO) {
			throw new SoldOutException("Drink "+drink+ " is sold out");
		}
		
		return result;
	}
}
