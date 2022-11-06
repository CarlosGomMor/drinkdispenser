package org.sdworx.drinkdispenser.drinkdispenser.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.sdworx.drinkdispenser.drinkdispenser.exception.SoldOutException;

public class Stock {
	
	private Integer ZERO = Integer.valueOf(0);
	private Integer ONE = Integer.valueOf(1);
	
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
	
	public void getDrinkFromStock (Drink drink) throws SoldOutException {
		
		drinks.put(drink, getStock(drink)-ONE);
	}
	
	public BigDecimal getDrinkPrice (String drink) throws SoldOutException {
		BigDecimal price = null;
		try {
			price = drinks.keySet().stream().filter(d->d.equals(new Drink(drink))).findAny().get().getPrice();
		} catch (NoSuchElementException e) {
			throw new SoldOutException("Drink "+drink+" is sold out");
		}
		return price;
	}
}