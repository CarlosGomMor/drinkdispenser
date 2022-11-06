 package org.sdworx.drinkdispenser.drinkdispenser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.sdworx.drinkdispenser.drinkdispenser.exception.NoChangeException;
import org.sdworx.drinkdispenser.drinkdispenser.exception.NotEnoughAvailableAmountException;
import org.sdworx.drinkdispenser.drinkdispenser.exception.SoldOutException;
import org.sdworx.drinkdispenser.drinkdispenser.model.Coin;
import org.sdworx.drinkdispenser.drinkdispenser.model.Drink;
import org.sdworx.drinkdispenser.drinkdispenser.model.EmployeeSelection;
import org.sdworx.drinkdispenser.drinkdispenser.model.Stock;

public class DrinkDispenser {
	
	private String CANCEL= "CANCEL";
	
	private Integer ZERO = Integer.valueOf(0);
	private Integer ONE = Integer.valueOf(1);
	private Integer TWO = Integer.valueOf(2);

	private Stock stock = new Stock();
	
	private BigDecimal availableAmount= BigDecimal.ZERO;

	private List<Coin> availableCoins = new ArrayList<Coin>();
	
	private Map<Coin, Integer> dispenserCoins = new HashMap<Coin, Integer>();
	
	public EmployeeSelection getEmployeeSelection(String selection) throws SoldOutException, NotEnoughAvailableAmountException, NoChangeException {
		EmployeeSelection result = new EmployeeSelection();
		
		if (selection.equals(CANCEL)) {
			result.setChangeCoins(this.availableCoins);
			this.availableCoins = new ArrayList<Coin>();
		} else {
			BigDecimal drinkPrice = this.stock.getDrinkPrice(selection);
			if (checkEnoughAvailableAmount(drinkPrice)) {
				result.setDrink(getSelectedDrink(new Drink(selection, drinkPrice)));
				result.setChangeCoins(getChange(drinkPrice));
				
			} else {
				throw new NotEnoughAvailableAmountException("Drink "+selection+" costs "+drinkPrice+". Select another drink or insert more coins");
			}
		}
		
		return result;
	}
	
	
	private Drink getSelectedDrink(Drink drink) throws SoldOutException {
		
		stock.getDrinkFromStock(drink);
		
		return drink;
	}
	
	private List<Coin> getChange (BigDecimal drinkPrice) throws NoChangeException {
		List<Coin> coins = new ArrayList<Coin>();
		
		BigDecimal changeAmount = this.availableAmount.subtract(drinkPrice);
		
		SortedSet<Coin> keySet = new TreeSet<Coin>(Collections.reverseOrder());
		
		keySet.addAll(this.dispenserCoins.keySet());
		
		for (Coin coin:keySet) {
			if (dispenserCoins.get(coin)>ZERO) {
				for (int i=ZERO; i<dispenserCoins.get(coin) && changeAmount.compareTo(BigDecimal.ZERO)>ZERO;i++) {
					if (!(changeAmount.subtract(coin.getValue()).compareTo(BigDecimal.ZERO)<ZERO)) {
						changeAmount = changeAmount.subtract(coin.getValue()) ;
						coins.add(coin);
					} 
				}
			}
		}
		
		if (changeAmount.setScale(TWO).equals(BigDecimal.ZERO.setScale(TWO))) {
			substractChangeCoins(coins);
		} else {
			throw new NoChangeException("Not enough change available in the dispenser");
		}
		
		return coins;
	}
	
	private void substractChangeCoins(List<Coin> coins) {
		for (Coin coin:coins) {
			this.dispenserCoins.put(coin, this.dispenserCoins.get(coin)-ONE);
		}
	}
	
	private Boolean checkEnoughAvailableAmount(BigDecimal drinkPrice) {
		return drinkPrice.compareTo(this.availableAmount)<ZERO;
	}
	
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}

	private void addToAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = this.availableAmount.add(availableAmount);
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
	
	public DrinkDispenser(Stock stock, Map<Coin, Integer> dispenserCoins) {
		super();
		this.stock = stock;
		this.dispenserCoins = dispenserCoins;
	}
	
	public Map<Coin, Integer> getDispenserCoins() {
		return dispenserCoins;
	}

	public void setDispenserCoins(Map<Coin, Integer> dispenserCoins) {
		this.dispenserCoins = dispenserCoins;
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
		return getStock(new Drink(drink, BigDecimal.ZERO));
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<Coin> getAvailableCoins() {
		return availableCoins;
	}
	
}
