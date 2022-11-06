package org.sdworx.drinkdispenser.drinkdispenser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private String CANCEL = "CANCEL";
	
	@Test
	public void getStock_GivenDispenserHasStock_ShouldBeShown() throws SoldOutException {
		DrinkDispenser dispenser = getFilledUpDrinkDispenser();
		
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
		DrinkDispenser dispenser = getFilledUpDrinkDispenser();
		
		dispenser.insertCoin(Coin.FIVE_CENTS);
		
		assertEquals(dispenser.getAvailableAmount(),Double.valueOf(0.05));
		assertTrue(dispenser.getAvailableCoins().contains(Coin.FIVE_CENTS));
		
		dispenser.insertCoin(Coin.FIFTY_CENTS);
		
		assertEquals(dispenser.getAvailableAmount(),Double.valueOf(0.55));
		assertTrue(dispenser.getAvailableCoins().contains(Coin.FIVE_CENTS));
		assertTrue(dispenser.getAvailableCoins().contains(Coin.FIFTY_CENTS));
	}
	
	@Test
	public void getEmployeeSelection_GivenThereIsAvailableAmountAndDrink_ShouldReturnDrinkAndChange() {
		DrinkDispenser dispenser = getFilledUpDrinkDispenser();

		dispenser.insertCoin(Coin.TWO_EURO);
		
		EmployeeSelection selection = dispenser.getEmployeeSelection(REDBULL);
		
		assertEquals(new Drink(REDBULL, Double.valueOf(1.25)), selection.getDrink());
		assertEquals(Double.valueOf(0.75), selection.getChangeAmount());
	}
	
	@Test
	public void getEmployeeSelection_GivenThereIsAvailableCoinsInDispenser_ShouldReturnCorrectCoinsAndShouldBeSubstractedFromDispenser() {
		DrinkDispenser dispenser = getFilledUpDrinkDispenser();

		dispenser.insertCoin(Coin.TWO_EURO);
		
		EmployeeSelection selection = dispenser.getEmployeeSelection(WATER);
		
		List<Coin> coinsToCheckAgainst = Arrays.asList(Coin.ONE_EURO, Coin.FIFTY_CENTS);
		
		assertEquals(Collections.sort(coinsToCheckAgainst), Collections.sort(selection.getChangeCoins()));
		
		assertEquals(dispenser.getDispenserCoins().get(Coin.ONE_EURO), Integer.valueOf(9));
		assertEquals(dispenser.getDispenserCoins().get(Coin.FIFTY_CENTS), Integer.valueOf(9));
		
	}
	
	@Test
	public void getEmployeeSelection_GivenThereIsNoAvailableChange_ShouldReturnInsertedCoins() {
		DrinkDispenser dispenser = getFilledUpDispenserWithoutChange();

		dispenser.insertCoin(Coin.ONE_EURO);
		dispenser.insertCoin(Coin.ONE_EURO);
		
		List<Coin> coinsToCheckAgainst = Arrays.asList(Coin.ONE_EURO, Coin.ONE_EURO);
		
		EmployeeSelection selection = dispenser.getEmployeeSelection(CANCEL);
		
		assertNull(selection.getDrink());
		
		assertEquals(Collections.sort(coinsToCheckAgainst), Collections.sort(selection.getChangeCoins()));
		
	}
	
	@Test
	public void getEmployeeSelection_Cancel_ShouldReturnInsertedCoins() {
		DrinkDispenser dispenser = getFilledUpDispenserWithoutChange();

		dispenser.insertCoin(Coin.FIFTY_CENTS);
		dispenser.insertCoin(Coin.ONE_EURO);
		
		List<Coin> coinsToCheckAgainst = Arrays.asList(Coin.ONE_EURO, Coin.FIFTY_CENTS);
		
		EmployeeSelection selection = dispenser.getEmployeeSelection(WATER);
		
		assertNull(selection.getDrink());
		
		assertEquals(Collections.sort(coinsToCheckAgainst), Collections.sort(selection.getChangeCoins()));
	}
	
	
	private DrinkDispenser getFilledUpDrinkDispenser () {
		return new DrinkDispenser(fillStock(), fillDispenserCoins());
	}
	
	private DrinkDispenser getFilledUpDispenserWithoutChange() {
		return new DrinkDispenser(fillStock());
	}
	
	private Stock fillStock() {
		Stock stock = new Stock();
		stock.add(new Drink(COCA, 1.0),2);
		stock.add(new Drink(REDBULL, 1.25),5);
		stock.add(new Drink(WATER, 0.5),3);
		stock.add(new Drink(ORANJE_JUICE, 1.95),10);
		return stock;
	}
	
	private Map<Coin, Integer> fillDispenserCoins(){
		Map<Coin, Integer> coinsToAdd = new HashMap<Coin, Integer>();

		coinsToAdd.put(Coin.FIFTY_CENTS, Integer.valueOf(10));
		coinsToAdd.put(Coin.FIVE_CENTS, Integer.valueOf(20));
		coinsToAdd.put(Coin.ONE_EURO, Integer.valueOf(10));
		coinsToAdd.put(Coin.TEN_CENTS, Integer.valueOf(30));
		coinsToAdd.put(Coin.TWO_EURO, Integer.valueOf(10));
		coinsToAdd.put(Coin.TWENTY_CENTS, Integer.valueOf(30));
		
		
		
		return coinsToAdd;
		
	}
	
	
	
	
}
