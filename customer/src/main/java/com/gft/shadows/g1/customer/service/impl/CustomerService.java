package com.gft.shadows.g1.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gft.shadows.g1.customer.dao.entity.CustomerDAO;
import com.gft.shadows.g1.customer.dao.entity.Respuesta;
import com.gft.shadows.g1.customer.dao.repository.CustomerRepository;
import com.gft.shadows.g1.customer.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public CustomerDAO createCustomer(CustomerDAO customerDAO) {
		CustomerDAO customerDAOAlta = null;
		if(customerDAO != null) {
			customerDAOAlta = customerRepository.save(customerDAO);
		}
		return customerDAOAlta;
	}

	@Override
	public CustomerDAO findCustomer(CustomerDAO customerDAOId) {
		Optional<CustomerDAO> customerDAO = null;
		if(customerDAOId != null && customerDAOId.getId()>0) {
			customerDAO = customerRepository.findById(customerDAOId.getId());
		}
		return customerDAO != null && customerDAO.isPresent()?customerDAO.get():null;
	}

	@Override
	public List<CustomerDAO> findAllCustomer() {
		Iterable<CustomerDAO> iterator = customerRepository.findAll();
		List<CustomerDAO> customerDAOList = (List<CustomerDAO>)iterator;
		return customerDAOList;
	}

	@Override
	public Respuesta deleteCustomer(CustomerDAO customerDAO) {
		Respuesta respuesta = new Respuesta();
		if(customerDAO != null && customerDAO.getId()>0 && customerRepository.existsById(customerDAO.getId())) {
			customerRepository.deleteById(customerDAO.getId());
			respuesta.setCodigo("01");
			respuesta.setMensaje("Elemento eliminado");
		}else {
			respuesta.setCodigo("02");
			respuesta.setMensaje("Elemento no eliminado");
		}
		return respuesta;
	}

	@Override
	public CustomerDAO updateCustomer(CustomerDAO customerDAO) {
		CustomerDAO customerDAORecuperado = null;
		if(customerDAO != null && customerDAO.getId()>0 && customerRepository.existsById(customerDAO.getId())) {
			customerDAORecuperado = customerRepository.findById(customerDAO.getId()).get();
			customerDAORecuperado.setBirthDate(!StringUtils.isEmpty(customerDAO.getBirthDate())?customerDAO.getBirthDate():customerDAORecuperado.getBirthDate());
			customerDAORecuperado.setGenero(!StringUtils.isEmpty(customerDAO.getGenero())?customerDAO.getGenero():customerDAORecuperado.getGenero());
			customerDAORecuperado.setName(!StringUtils.isEmpty(customerDAO.getName())?customerDAO.getName():customerDAORecuperado.getName());
			customerDAORecuperado.setNss(!StringUtils.isEmpty(customerDAO.getNss())?customerDAO.getNss():customerDAORecuperado.getNss());
			customerDAORecuperado = customerRepository.save(customerDAORecuperado);			
		}
		return customerDAORecuperado;
	}

}
