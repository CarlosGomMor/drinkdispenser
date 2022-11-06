package org.sdworx.drinkdispenser.drinkdispenser.model;

public enum Coin {
	FIVE_CENTS(0.05),
	TEN_CENTS(0.1),
	TWENTY_CENTS(0.2),
	FIFTY_CENTS(0.5),
	ONE_EURO(1.0),
	TWO_EURO(2.0);
	
	private Double value;
	
	public Double getValue() {
		return value;
	}

	private Coin(Double value) {
		this.value = value;
	}

}
