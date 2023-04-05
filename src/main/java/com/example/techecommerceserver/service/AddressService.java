
package com.example.techecommerceserver.service;

import com.os.exception.AddressException;
import com.os.exception.CustomerException;
import com.os.exception.SessionLoginException;
import com.os.model.Address;

import java.util.List;

public interface AddressService {

	public Address updateAddressByUserId(Address address, Integer userId, String key)
			throws AddressException, CustomerException, SessionLoginException;

	public List<Address> viewAllAddress(String key) throws AddressException, SessionLoginException;

	public Address viewAddressByUserId(Integer userId, String key) throws CustomerException, SessionLoginException;

}
