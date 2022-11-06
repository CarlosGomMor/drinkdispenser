package org.sdworx.drinkdispenser.drinkdispenser.model;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeSelection {

	private Drink drink = null;
	private List<Coin> changeCoins;
	
	public BigDecimal getChangeAmount() {
		BigDecimal result =  BigDecimal.ZERO;
		
		for (Coin coin:changeCoins) {
			result = result.add(coin.getValue());
		}
		
		return result;
	}
	
	public Drink getDrink() {
		return drink;
	}
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	public List<Coin> getChangeCoins() {
		return changeCoins;
	}
	public void setChangeCoins(List<Coin> change) {
		this.changeCoins = change;
	}
	
}
