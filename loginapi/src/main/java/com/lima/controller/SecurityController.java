package com.lima.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.entity.Account;
import com.lima.jwt.JwtUtility;
import com.lima.payload.request.LoginRequest;
import com.lima.payload.response.JwtLoginResponse;
import com.lima.service.IAccountService;
import com.lima.servicee.impl.AccountDetailsImpl;
import com.lima.vadidation.LoginRequestValidator;

@RestController
@RequestMapping("api/public")
@CrossOrigin("http://localhost:3000")
public class SecurityController {

	@Autowired
	private JwtUtility jwtUtility;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private LoginRequestValidator loginByRequestDTOValidator;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest,
			BindingResult bindingResult) throws Exception {
		loginByRequestDTOValidator.validate(loginRequest, bindingResult);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		Authentication authenticatation = authenticate(loginRequest.getUsername(), loginRequest.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authenticatation);
		String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
		AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		Account account = accountService.findAccountByUserName(loginRequest.getUsername());
		// Member member = memberService.findByAccountIdAndDeleteFlag(account.getId(),
		// false);
		return ResponseEntity
				.ok(new JwtLoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles, account));
	}

	private Authentication authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
