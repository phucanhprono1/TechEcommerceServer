package com.example.techecommerceserver.service;


import com.example.techecommerceserver.dto.LoginFacebookDTO;
import com.example.techecommerceserver.exception.LoginException;
import com.example.techecommerceserver.dto.LoginDTO;
import com.example.techecommerceserver.response.LoginResponse;

public interface LoginService {

	public LoginResponse loginAccount(LoginDTO loginDTO) throws LoginException;
	public LoginResponse loginFacebook(LoginFacebookDTO loginDTO) throws LoginException;

	public String logoutAccount(String role, String key) throws LoginException;

}
