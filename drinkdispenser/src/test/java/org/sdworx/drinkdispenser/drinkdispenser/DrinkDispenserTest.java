package org.sdworx.drinkdispenser.drinkdispenser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class DrinkDispenserTest {
	
	private String COCA = "Coca";
	private String REDBULL = "Redbull";
	private String WATER = "Water";
	private String ORANJE_JUICE = "Orange juice";
	
	@Test
	public void getStock_GivenDispenserHasStock_ShouldBeShown() {
		DrinkDispenser dispenser = new DrinkDispenser(fillStock());
		
		assertEquals(2, dispenser.getStock(COCA));
		assertEquals(5, dispenser.getStock(REDBULL));
		assertEquals(3, dispenser.getStock(WATER));
		assertEquals(10, dispenser.getStock(ORANJE_JUICE));
		
	}
	
	@Test
	public void getStock_GivenDispenserDoesNotHaveStock_ShouldLaunchException() {
		DrinkDispenser dispenser = new DrinkDispenser();
	
		assertThrows(SoldOutException.class, dispenser.getStock(COCA));
		assertThrows(SoldOutException.class, dispenser.getStock(REDBULL));
		assertThrows(SoldOutException.class, dispenser.getStock(WATER));
		assertThrows(SoldOutException.class, dispenser.getStock(ORANJE_JUICE));
		
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
