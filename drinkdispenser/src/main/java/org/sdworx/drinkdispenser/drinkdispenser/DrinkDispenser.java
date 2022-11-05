 package org.sdworx.drinkdispenser.drinkdispenser;

import org.sdworx.drinkdispenser.drinkdispenser.exception.SoldOutException;
import org.sdworx.drinkdispenser.drinkdispenser.model.Drink;
import org.sdworx.drinkdispenser.drinkdispenser.model.Stock;

public class DrinkDispenser {

	private Stock stock = new Stock();

	public DrinkDispenser(Stock stock) {
		super();
		this.stock = stock;
	}
	
	public DrinkDispenser() {
		super();
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public Integer getStock(Drink drink) throws SoldOutException {
		return stock.getStock(drink);
	}
	
	public Integer getStock(String drink) throws SoldOutException {
		return getStock(new Drink(drink,0.0));
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
}
