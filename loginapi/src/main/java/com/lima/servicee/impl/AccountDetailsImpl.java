package com.lima.servicee.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lima.entity.Account;

/**
 * Nguyen Van Linh made it
 */
public class AccountDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private Boolean enabled;

	@JsonIgnore
	private String password;
	List<GrantedAuthority> authorities = null;

	public AccountDetailsImpl(Integer id, String username, String password, List<GrantedAuthority> authorities,
			Boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
	}

// This func help you guys get account information to AccountDetailService
	public static AccountDetailsImpl build(Account account) {
		List<GrantedAuthority> authorities = account.getAccountRoleList().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getName())).collect(Collectors.toList());
		return new AccountDetailsImpl(account.getId(), account.getUserName(), account.getEncryptPw(), authorities,
				account.getIsEnabled());
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public Integer getId() {
		return id;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AccountDetailsImpl account = (AccountDetailsImpl) o;
		return Objects.equals(id, account.id);
	}


}
