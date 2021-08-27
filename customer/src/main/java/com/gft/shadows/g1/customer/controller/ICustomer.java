package com.gft.shadows.g1.customer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gft.shadows.g1.customer.controller.dto.CustomerDTO;

public interface ICustomer {

	ResponseEntity<CustomerDTO> createCustomer(CustomerDTO CustomerDTO);	
	ResponseEntity<CustomerDTO> findCustomer(Long CustomerDTOId);
	ResponseEntity<List<CustomerDTO>> findAllCustomer();
	ResponseEntity<HttpStatus>deleteCustomer(Long CustomerDTOId);
	ResponseEntity<CustomerDTO> updateCustomer(Long CustomerDTOId,CustomerDTO CustomerDTO);
	
}
