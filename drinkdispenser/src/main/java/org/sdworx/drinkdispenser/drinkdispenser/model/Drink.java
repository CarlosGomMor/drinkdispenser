package org.sdworx.drinkdispenser.drinkdispenser.model;

import java.util.Objects;

public class Drink {
	private final String name;
	private final Double price;
	
	public Drink(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
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
