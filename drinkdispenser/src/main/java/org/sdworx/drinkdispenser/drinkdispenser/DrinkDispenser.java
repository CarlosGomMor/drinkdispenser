 package org.sdworx.drinkdispenser.drinkdispenser;

import java.util.ArrayList;
import java.util.List;

import org.sdworx.drinkdispenser.drinkdispenser.exception.SoldOutException;
import org.sdworx.drinkdispenser.drinkdispenser.model.Coin;
import org.sdworx.drinkdispenser.drinkdispenser.model.Drink;
import org.sdworx.drinkdispenser.drinkdispenser.model.Stock;

public class DrinkDispenser {

	private Stock stock = new Stock();
	
	private Double availableAmount=0.0;

	private List<Coin> availableCoins = new ArrayList<Coin>();
	
	public Double getAvailableAmount() {
		return availableAmount;
	}

	private void addToAvailableAmount(Double availableAmount) {
		this.availableAmount += availableAmount;
	}
	
	private void addToAvailableCoins (Coin coin) {
		this.availableCoins.add(coin);
	}
	
	public void insertCoin(Coin coin) {
		addToAvailableAmount(coin.getValue());
		addToAvailableCoins(coin);
	}

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

	public List<Coin> getAvailableCoins() {
		return availableCoins;
	}
	
}
