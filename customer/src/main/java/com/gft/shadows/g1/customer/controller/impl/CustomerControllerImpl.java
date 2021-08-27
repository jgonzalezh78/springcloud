package com.gft.shadows.g1.customer.controller.impl;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.shadows.g1.customer.controller.ICustomer;
import com.gft.shadows.g1.customer.controller.dto.CustomerDTO;
import com.gft.shadows.g1.customer.controller.mapper.ICustomerMapper;
import com.gft.shadows.g1.customer.dao.client.AccountClient;
import com.gft.shadows.g1.customer.dao.client.AccountDTO;
import com.gft.shadows.g1.customer.dao.entity.CustomerDAO;
import com.gft.shadows.g1.customer.dao.entity.Respuesta;
import com.gft.shadows.g1.customer.service.ICustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl  implements ICustomer{

	
	@Autowired
	private ICustomerMapper customerMapper;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private AccountClient accountClient;
	
	@PostMapping()
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		System.out.println("createCustomer");
		CustomerDTO customerDTOSalida = null;
		if(customerDTO != null) {
			customerDTOSalida  = customerMapper.CustomerDAOTocustomerDTO(
									customerService.createCustomer(
											customerMapper.customerDTOToCustomerDAO(customerDTO)));
			
		}
		
		HttpHeaders headers = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
											 .buildAndExpand(customerDTOSalida.getId()).toUri();
		headers.setLocation(uri);
		
		return new ResponseEntity(customerDTOSalida,headers,HttpStatus.CREATED);
	}

	/*@HystrixCommand(fallbackMethod="metodoApoyo",
			threadPoolKey="customerThreadPool",
			threadPoolProperties={@HystrixProperty(name="coreSize",value="500"),
		    					  @HystrixProperty(name="maxQueueSize",value="20")},
	commandProperties={
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="500"),
			@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="200"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="75"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="7000"),
			@HystrixProperty(name="metrics.rollingStats.numBuckets",value="5")
			})*/
	@GetMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> findCustomer(@PathVariable("id") Long customerDTOId) {
		System.out.println("findCustomer");
		//sleep();
		CustomerDTO customerDTO = new CustomerDTO(null,null,null,null,customerDTOId);
		customerDTO = customerMapper.CustomerDAOTocustomerDTO(
								customerService.findCustomer(
										customerMapper.customerDTOToCustomerDAO(customerDTO)));
		
		try {
			
			ResponseEntity<List<AccountDTO>> responseEntity  = accountClient.findAccountByCustomer(customerDTOId);
			System.out.println("Respuesta["+responseEntity+"]");
			List<AccountDTO> accountDTO = responseEntity.getBody();
			System.out.println("accountDTO["+accountDTO+"]");
		}catch(Exception e) {
			System.out.println("Error["+e.getMessage()+"]");
		}
				
		return new ResponseEntity(customerDTO,customerDTO!= null?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
		//return customerDTO;
	}
	
	public ResponseEntity<CustomerDTO> metodoApoyo(Long customerDTOId){
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName("Nombre de prueba");
		return new ResponseEntity(customerDTO,HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAllCustomer() {
		System.out.println("findAllCustomer");
		List<CustomerDAO> customerDAOList = customerService.findAllCustomer();
		List<CustomerDTO> customerDTOList = 		customerDAOList
													.stream()
													.map(customerDAO -> customerMapper.CustomerDAOTocustomerDTO(customerDAO))
													.collect(Collectors.toList());
			
		return new ResponseEntity(customerDTOList,customerDTOList != null && customerDTOList.size()>0?HttpStatus.FOUND:HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long customerDTOId) {
		System.out.println("deleteCustomer");
		CustomerDTO customerDTO = new CustomerDTO(null,null,null,null,customerDTOId);
		Respuesta respuesta = customerService.deleteCustomer(customerMapper.customerDTOToCustomerDAO(customerDTO));
		return new ResponseEntity(respuesta.getCodigo().equals("01")?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long customerDTOId, 
														 @RequestBody CustomerDTO customerDTO) {
		System.out.println("updateCustomer");
		customerDTO.setId(customerDTOId);
		customerDTO = customerMapper.CustomerDAOTocustomerDTO(
						 customerService.updateCustomer(customerMapper.customerDTOToCustomerDAO(customerDTO)));
		return new ResponseEntity(customerDTO,customerDTO!= null?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}

	private void sleep(){
		Random random = new Random();
		int valor = random.nextInt(3);
		System.out.println("Se dormira ["+valor*10+"] milisegundos");
		try {
			Thread.sleep(valor*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
}
