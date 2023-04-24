package com.example.techecommerceserver.service;


import com.example.techecommerceserver.exception.LoginException;
import com.example.techecommerceserver.model.LoginDTO;

public interface LoginService {

	public String loginAccount(LoginDTO loginDTO) throws LoginException;

	public String logoutAccount(String role, String key) throws LoginException;

}
