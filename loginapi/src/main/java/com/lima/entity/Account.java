package com.lima.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "encrypt_pw")
	private String encryptPw;

	@Column(name = "is_enabled")
	private Boolean isEnabled;

	@Column(name = "verification_code")
	private String verificationCode;

	@Column(name = "email")
	private String email;

	@Column(name = "delete_flag")
	private Boolean deleteFlag;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<AccountRole> accountRoleList;

	public Account() {

	}

	public Account(String userName, String email, String encryptPw) {
		this.userName = userName;
		this.email = email;
		this.encryptPw = encryptPw;
	}

	public Account(String userName, String email, String encryptPw, Boolean isEnable, String verificationCode) {
		this.userName = userName;
		this.email = email;
		this.encryptPw = encryptPw;
		this.isEnabled = isEnable;
		this.verificationCode = verificationCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccountRoleList(List<AccountRole> accountRoleList) {
		this.accountRoleList = accountRoleList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptPw() {
		return encryptPw;
	}

	public void setEncryptPw(String encryptPw) {
		this.encryptPw = encryptPw;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AccountRole> getAccountRoleList() {
		return accountRoleList;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}