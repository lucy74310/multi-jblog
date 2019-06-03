package com.cafe24.jblog.vo;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	
	@NotEmpty
	@Length(min=2, max=15)
	private String id;
	
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;
	
	@NotEmpty
	@Length(min=4)
	private String password;
	private String joinDate;
	
	@AssertTrue
	private Boolean idCheck;
	
	@AssertTrue
	@NotNull
	private Boolean agreeProv;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
	public Boolean getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(Boolean idCheck) {
		this.idCheck = idCheck;
	}
	public Boolean getAgreeProv() {
		return agreeProv;
	}
	public void setAgreeProv(Boolean agreeProv) {
		this.agreeProv = agreeProv;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", joinDate=" + joinDate
				+ ", idCheck=" + idCheck + ", agreeProv=" + agreeProv + "]";
	}
	
	
}
