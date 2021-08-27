package com.gft.shadows.g1.customer.controller.mapper;

import org.mapstruct.Mapper;

import com.gft.shadows.g1.customer.controller.dto.CustomerDTO;
import com.gft.shadows.g1.customer.dao.entity.CustomerDAO;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

	CustomerDAO customerDTOToCustomerDAO(CustomerDTO customerDTO);
	CustomerDTO CustomerDAOTocustomerDTO(CustomerDAO customerDAO );
		
}
