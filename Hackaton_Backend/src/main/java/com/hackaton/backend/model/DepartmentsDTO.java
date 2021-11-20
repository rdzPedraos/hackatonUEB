package com.hackaton.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class DepartmentsDTO {
	@Id
	@Column(name = "code")
	private int code;
	private int code_country_department;
	private String name;

	
	public DepartmentsDTO() {
		
	}
	
	public DepartmentsDTO(int code, int code_country_department, String name) {
		super();
		this.code = code;
		this.code_country_department = code_country_department;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode_country_department() {
		return code_country_department;
	}
	public void setCode_country_department(int code_country_department) {
		this.code_country_department = code_country_department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
