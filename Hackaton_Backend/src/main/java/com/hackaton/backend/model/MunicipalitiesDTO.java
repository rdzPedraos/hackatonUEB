package com.hackaton.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "municipalities")
public class MunicipalitiesDTO {
	@Id
	@Column(name = "code")
	private int code;
	private int code_department_municipality;
	private String name;
	
	public MunicipalitiesDTO() {
		
	}
	
	public MunicipalitiesDTO(int code, int code_department_municipality, String name) {
		super();
		this.code = code;
		this.code_department_municipality = code_department_municipality;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode_department_municipality() {
		return code_department_municipality;
	}
	public void setCode_department_municipality(int code_department_municipality) {
		this.code_department_municipality = code_department_municipality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
