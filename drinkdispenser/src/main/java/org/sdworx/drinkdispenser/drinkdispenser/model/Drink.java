package org.sdworx.drinkdispenser.drinkdispenser.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Drink {
	private final String name;
	private final BigDecimal price;
	
	public Drink(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Drink(String name) {
		super();
		this.name = name;
		this.price = BigDecimal.ZERO;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Drink))
			return false;
		Drink other = (Drink) obj;
		return Objects.equals(name, other.name);
	}
}
