package com.example.techecommerceserver.implementation;


import com.example.techecommerceserver.dto.LoginFacebookDTO;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.LoginException;
import com.example.techecommerceserver.model.Admin;
import com.example.techecommerceserver.model.CurrentUserSession;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.dto.LoginDTO;
import com.example.techecommerceserver.repository.AdminRepo;
import com.example.techecommerceserver.repository.CurrentUserSessionRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.response.LoginResponse;
import com.example.techecommerceserver.service.CustomerService;
import com.example.techecommerceserver.service.LoginService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public LoginResponse loginAccount(LoginDTO loginDTO) throws LoginException {

		if (!loginDTO.getRole().equalsIgnoreCase("customer") && !loginDTO.getRole().equalsIgnoreCase("admin"))
			throw new LoginException("Please enter a valid role");

		if (loginDTO.getRole().equalsIgnoreCase("customer")) {
			Customer customer = customerRepo.findByUsername(loginDTO.getUsername());
			if (customer == null)
				throw new LoginException("Invalid email");

			if (customer.getPassword().equals(loginDTO.getPassword())) {

				CurrentUserSession cuurSession = sessionRepo.findByUsername(loginDTO.getUsername());

				if (cuurSession != null)
					throw new LoginException("User already logged-In!");

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setUsername(loginDTO.getUsername());
				currentUserSession.setLoginDateTime(LocalDateTime.now());
				currentUserSession.setRole("customer");
				String privateKey = RandomString.make(10);
				currentUserSession.setPrivateKey(privateKey);

				sessionRepo.save(currentUserSession);
				LoginResponse lr = new LoginResponse("Login Successfully!",customer.getCId(),loginDTO.getUsername(),"customer",privateKey);
				return lr;
			} else {
				throw new LoginException("Please Enter a valid password");
			}

		} else if (loginDTO.getRole().equalsIgnoreCase("admin")) {
			Admin admin = adminRepo.findByEmail(loginDTO.getUsername());
			if (admin == null)
				throw new LoginException("Invalid email");

			if (admin.getPassword().equals(loginDTO.getPassword())) {

				CurrentUserSession cuurSession = sessionRepo.findByUsername(loginDTO.getUsername());

				if (cuurSession != null)
					throw new LoginException("User already logged-In!");

				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setUsername(loginDTO.getUsername());
				currentUserSession.setLoginDateTime(LocalDateTime.now());
				currentUserSession.setRole("admin");
				String privateKey = RandomString.make(10);
				currentUserSession.setPrivateKey(privateKey);
				LoginResponse lr = new LoginResponse("Login Successfully!",admin.getAdminId(),loginDTO.getUsername(),"admin",privateKey);
				sessionRepo.save(currentUserSession);
				return lr;
			} else {
				throw new LoginException("Please Enter a valid password");
			}
		}
		return null;
	}

	@Override
	public LoginResponse loginFacebook(LoginFacebookDTO loginDTO) throws LoginException {
		if (!loginDTO.getRole().equalsIgnoreCase("customer") && !loginDTO.getRole().equalsIgnoreCase("admin"))
			throw new LoginException("Please enter a valid role");



		if (loginDTO.getRole().equalsIgnoreCase("customer")) {
			Customer rq = new Customer(loginDTO.getName(),loginDTO.getUsername(),loginDTO.getPhone_number(),loginDTO.getEmail(),loginDTO.getPassword());
			Customer customer = customerRepo.findByUsername(loginDTO.getUsername());
			if (customer == null) {
				try {
					customerService.addCustomer(rq);
				} catch (CustomerException e) {
					throw new RuntimeException(e);
				}
			}
			else{
				if (customer.getPassword().equals(loginDTO.getPassword())) {

					CurrentUserSession cuurSession = sessionRepo.findByUsername(loginDTO.getUsername());

					if (cuurSession != null)
						throw new LoginException("User already logged-In!");

					CurrentUserSession currentUserSession = new CurrentUserSession();
					currentUserSession.setUsername(loginDTO.getUsername());
					currentUserSession.setLoginDateTime(LocalDateTime.now());
					currentUserSession.setRole("customer");
					String privateKey = RandomString.make(10);
					currentUserSession.setPrivateKey(privateKey);

					sessionRepo.save(currentUserSession);
					LoginResponse lr = new LoginResponse("Login Successfully!",customer.getCId(),loginDTO.getUsername(),"customer",privateKey);
					return lr;
				} else {
					throw new LoginException("Please Enter a valid password");
				}
			}
		}

		return null;
	}

	@Override
	public String logoutAccount(String role, String key) throws LoginException {

		if (!role.equalsIgnoreCase("customer") && !role.equalsIgnoreCase("admin"))
			throw new LoginException("Please enter a valid role");

		if (role.equalsIgnoreCase("customer")) {

			CurrentUserSession currSession = sessionRepo.findByPrivateKey(key);
			if (currSession == null)
				throw new LoginException("Invalid key");

			if (currSession.getRole().equalsIgnoreCase("customer")) {

				sessionRepo.delete(currSession);
				return "Logged Out!";

			} else
				throw new LoginException("Invalid role");

		} else if (role.equalsIgnoreCase("admin")) {

			CurrentUserSession currSession = sessionRepo.findByPrivateKey(key);
			if (currSession == null)
				throw new LoginException("Invalid key");

			if (currSession.getRole().equalsIgnoreCase("admin")) {

				sessionRepo.delete(currSession);
				return "Logged Out!";

			} else
				throw new LoginException("Invalid role");

		} else
			throw new LoginException("Invalid role");
	}
}
