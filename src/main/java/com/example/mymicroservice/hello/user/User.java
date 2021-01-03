package com.example.mymicroservice.hello.user;

import java.util.Date;

//import com.shinhee.user.Past;
//import com.shinhee.user.Size;

public class User {

	
private Integer id;
	
	//@Size(min=2, message="Name should have atleasst 2 characters") //connected to UserResouce validation annot
	private String name;
	
	//@Past
	private Date birthDate;
	
	//create Constructor -> getter&setter -> toString

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
