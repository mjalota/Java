package com.munish.lms.libraryusers;

public abstract class Person implements LibUsers{
	private String firstName;
	private String secondName;
	private String postcode ;

	public Person(String firstName, String secondName, String postcode) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.postcode = postcode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}	
}
