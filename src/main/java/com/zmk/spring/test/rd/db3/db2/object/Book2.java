package com.zmk.spring.test.rd.db3.db2.object;

import java.math.BigDecimal;

public class Book2 {
	private Long id;
    private String name;
    private BigDecimal price;
    public Book2(String name,BigDecimal price) {
    	this.name = name;
    	this.price = price;
    }
    public Book2(Long id, String name,BigDecimal price) {
    	this.id = id;
    	this.name = name;
    	this.price = price;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
