package com.hackaton.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "info_user")
public class InfoUserDTO {
	@Id
	@Column(name = "code")
	private long code;
	private int code_municipality;
	private String name;
	private String last_name;
	
	private int age;
	private String address;
	private String zip_code;
	private String message;
	
	public InfoUserDTO() {
		
	}
	
	public InfoUserDTO(long code, int code_municipality, String name, String last_name, int age, String address,
			String zip_code, String message) {
		super();
		this.code = code;
		this.code_municipality = code_municipality;
		this.name = name;
		this.last_name = last_name;
		this.age = age;
		this.address = address;
		this.zip_code = zip_code;
		this.message = message;
	}

	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public int getCode_municipality() {
		return code_municipality;
	}
	public void setCode_municipality(int code_municipality) {
		this.code_municipality = code_municipality;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
}
