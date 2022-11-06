package org.sdworx.drinkdispenser.drinkdispenser.model;

import java.math.BigDecimal;

public enum Coin {
	FIVE_CENTS(BigDecimal.valueOf(0.05)),
	TEN_CENTS(BigDecimal.valueOf(0.1)),
	TWENTY_CENTS(BigDecimal.valueOf(0.2)),
	FIFTY_CENTS(BigDecimal.valueOf(0.5)),
	ONE_EURO(BigDecimal.valueOf(1.0)),
	TWO_EURO(BigDecimal.valueOf(2.0));
	
	private BigDecimal value;
	
	public BigDecimal getValue() {
		return value;
	}

	private Coin(BigDecimal value) {
		this.value = value;
	}

}
