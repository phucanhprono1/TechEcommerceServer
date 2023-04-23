
package com.example.techecommerceserver.service;


import com.example.techecommerceserver.exception.AddressException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.SessionLoginException;
import com.example.techecommerceserver.model.Address;

import java.util.List;

public interface AddressService {

	public Address updateAddressByUserId(Address address, Integer userId, String key)
			throws AddressException, CustomerException, SessionLoginException;

	public List<Address> viewAllAddress(String key) throws AddressException, SessionLoginException;

	public Address viewAddressByUserId(Integer userId, String key) throws CustomerException, SessionLoginException;

}
