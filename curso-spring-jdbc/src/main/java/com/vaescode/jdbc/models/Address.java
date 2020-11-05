package com.vaescode.jdbc.models;

public class Address {

	private Integer id;
	private String street;
	private String number;
	private Integer postal_code;
	private Integer employeeId;

	public Address() {
	}

	public Address(String street, String number, Integer postal_code, Integer employeeId) {
		this.street = street;
		this.number = number;
		this.postal_code = postal_code;
		this.employeeId = employeeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(Integer postal_code) {
		this.postal_code = postal_code;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
