package org.sdworx.drinkdispenser.drinkdispenser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sdworx.drinkdispenser.drinkdispenser.exception.SoldOutException;
import org.sdworx.drinkdispenser.drinkdispenser.model.Coin;
import org.sdworx.drinkdispenser.drinkdispenser.model.Drink;
import org.sdworx.drinkdispenser.drinkdispenser.model.Stock;

public class DrinkDispenserTest {
	
	private String COCA = "Coca";
	private String REDBULL = "Redbull";
	private String WATER = "Water";
	private String ORANJE_JUICE = "Orange juice";
	private String SOLD_OUT_MESSAGE = "Drink %s is sold out";
	
	@Test
	public void getStock_GivenDispenserHasStock_ShouldBeShown() throws SoldOutException {
		DrinkDispenser dispenser = new DrinkDispenser(fillStock());
		
		assertEquals(Integer.valueOf(2), dispenser.getStock(COCA));
		assertEquals(Integer.valueOf(5), dispenser.getStock(REDBULL));
		assertEquals(Integer.valueOf(3), dispenser.getStock(WATER));
		assertEquals(Integer.valueOf(10), dispenser.getStock(ORANJE_JUICE));
		
	}
	
	@Test
	public void getStock_GivenDispenserDoesNotHaveStock_ShouldLaunchException() {
		DrinkDispenser dispenser = new DrinkDispenser();
	
		assertThrows(String.format(SOLD_OUT_MESSAGE, COCA),SoldOutException.class,()-> dispenser.getStock(COCA));
		assertThrows(String.format(SOLD_OUT_MESSAGE, REDBULL),SoldOutException.class,()-> dispenser.getStock(REDBULL));
		assertThrows(String.format(SOLD_OUT_MESSAGE, WATER),SoldOutException.class,()-> dispenser.getStock(WATER));
		assertThrows(String.format(SOLD_OUT_MESSAGE, ORANJE_JUICE),SoldOutException.class,()-> dispenser.getStock(ORANJE_JUICE));
	}
	
	@Test
	public void insertCoin_GivenAcceptedCoin_ShouldBeAddedToAvailableAmountAndCoins() {
		DrinkDispenser dispenser = new DrinkDispenser(fillStock());
		
		dispenser.insertCoin(Coin.FIVE_CENTS);
		
		assertEquals(dispenser.getAvailableAmount(),Double.valueOf(0.05));
		assertTrue(dispenser.getAvailableCoins().contains(Coin.FIVE_CENTS));
		
		dispenser.insertCoin(Coin.FIFTY_CENTS);
		
		assertEquals(dispenser.getAvailableAmount(),Double.valueOf(0.55));
		assertTrue(dispenser.getAvailableCoins().contains(Coin.FIVE_CENTS));
		assertTrue(dispenser.getAvailableCoins().contains(Coin.FIFTY_CENTS));
	}
	
	private Stock fillStock () {
		Stock stock = new Stock();
		stock.add(new Drink(COCA, 1.0),2);
		stock.add(new Drink(REDBULL, 1.25),5);
		stock.add(new Drink(WATER, 0.5),3);
		stock.add(new Drink(ORANJE_JUICE, 1.95),10);
		return stock;
	}
	
	
	
	
}
