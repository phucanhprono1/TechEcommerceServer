package com.example.techecommerceserver.implementation;


import com.example.techecommerceserver.dto.CurrentCustomerDTO;
import com.example.techecommerceserver.exception.SessionLoginException;
import com.example.techecommerceserver.model.CurrentUserSession;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.repository.CurrentUserSessionRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.service.SessionLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginServiceImpl implements SessionLoginService {

	@Autowired
	private CurrentUserSessionRepo sessionRepo;
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public void checkAnyUserLoginStatus(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess == null)
			throw new SessionLoginException("User login required");
	}

	@Override
	public CurrentCustomerDTO getCurrentCustomer(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess == null)
			throw new SessionLoginException("User login required");
		else {
			Customer cs = customerRepo.findByUsername(currSess.getUsername());
			return new CurrentCustomerDTO(cs.getName(),cs.getUsername(),cs.getPhone_number(), cs.getEmail());
		}
	}

	@Override
	public void checkAdminUserLoginStatus(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess != null && !currSess.getRole().equalsIgnoreCase("admin"))
			throw new SessionLoginException("Admin login required");
	}

	@Override
	public void checkCustomerUserLoginStatus(String key) throws SessionLoginException {
		CurrentUserSession currSess = sessionRepo.findByPrivateKey(key);
		if (currSess != null && !currSess.getRole().equalsIgnoreCase("customer"))
			throw new SessionLoginException("Admin login required");
	}

}
