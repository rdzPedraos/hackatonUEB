package com.hackaton.backend.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "contract")
public class ContractsDTO {
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int code;
	
	private int code_user_lessee_contract;
	private int code_product_contract;
	
	@Temporal(TemporalType.DATE)
	private Date date_init;
	
	@Temporal(TemporalType.DATE)
	private Date date_final;
	
	public ContractsDTO() {
		
	}
	
	public ContractsDTO(int code, int code_user_owner_contract, int code_user_lessee_contract, int code_product_contract,
			Date date_init, Date date_final) {
		super();
		this.code = code;
		this.code_user_lessee_contract = code_user_lessee_contract;
		this.code_product_contract = code_product_contract;
		this.date_init = date_init;
		this.date_final = date_final;
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode_user_lessee_contract() {
		return code_user_lessee_contract;
	}
	public void setCode_user_lessee_contract(int code_user_lessee_contract) {
		this.code_user_lessee_contract = code_user_lessee_contract;
	}
	public int getCode_product_contract() {
		return code_product_contract;
	}
	public void setCode_product_contract(int code_product_contract) {
		this.code_product_contract = code_product_contract;
	}
	public Date getDate_init() {
		return date_init;
	}
	public void setDate_init(Date date_init) {
		this.date_init = date_init;
	}
	public Date getDate_final() {
		return date_final;
	}
	public void setDate_final(Date date_final) {
		this.date_final = date_final;
	}
	
		
}
