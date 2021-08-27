package com.gft.shadows.g1.customer.dao.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="microservicio-cuenta")
public interface AccountClient {

	
	@RequestMapping(method=RequestMethod.GET,
					value="/accounts/{id}",
					consumes="application/json")
	ResponseEntity<AccountDTO> findAccount(@PathVariable("id") Long id); 

	
	@RequestMapping(method=RequestMethod.GET,
			value="/accounts/customer/{id}",
			consumes="application/json")
	ResponseEntity<List<AccountDTO>> findAccountByCustomer(@PathVariable("id") Long id); 
	
}
