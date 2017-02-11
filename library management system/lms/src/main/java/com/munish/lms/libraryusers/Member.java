package com.munish.lms.libraryusers;

public class Member extends Person {
	private associationType userType ;

	public Member(String firstName, String secondName, String postcode, associationType userType) {
		super(firstName, secondName, postcode);
		this.userType =userType ;
	}

	public associationType getUserType() {
		return userType;
	}

	public void setUserType(associationType userType) {
		this.userType = userType;
	}

}
