package jpa;


import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity

public class FullTimeEmployee extends Employee {
	
	private BigDecimal salary;
	
	protected FullTimeEmployee() {
	}

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);//calling the parent class constructor.
		this.salary = salary;
	}

	

}