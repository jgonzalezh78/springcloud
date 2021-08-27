package com.gft.shadows.g1.customer.service;

import java.util.List;

import com.gft.shadows.g1.customer.dao.entity.CustomerDAO;
import com.gft.shadows.g1.customer.dao.entity.Respuesta;

public interface ICustomerService {

	CustomerDAO createCustomer(CustomerDAO customerDAO);	
	CustomerDAO findCustomer(CustomerDAO customerDAOId);
	List<CustomerDAO> findAllCustomer();
	Respuesta deleteCustomer(CustomerDAO customerDAOId);
	CustomerDAO updateCustomer(CustomerDAO customerDAO);
	
	
}
