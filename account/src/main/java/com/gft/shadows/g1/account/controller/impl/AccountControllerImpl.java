package com.gft.shadows.g1.account.controller.impl;

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

import com.gft.shadows.g1.account.controller.IAccount;
import com.gft.shadows.g1.account.controller.dto.AccountDTO;
import com.gft.shadows.g1.account.controller.mapper.IAccountMapper;
import com.gft.shadows.g1.account.dao.entity.AccountDAO;
import com.gft.shadows.g1.account.dao.entity.Respuesta;
import com.gft.shadows.g1.account.service.IAccountService;

@RestController
@RequestMapping("/accounts")
public class AccountControllerImpl implements IAccount {

	@Autowired
	private IAccountMapper accountMapper;

	@Autowired
	private IAccountService accountService;

	@PostMapping()
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
		System.out.println("createAccount");
		AccountDTO accountDTOSalida = null;
		if (accountDTO != null) {
			accountDTOSalida = accountMapper.AccountDAOToAccountDTO(
					accountService.createAccount(accountMapper.AccountDTOToAccountDAO(accountDTO)));

		}

		HttpHeaders headers = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(accountDTOSalida.getId()).toUri();
		headers.setLocation(uri);

		return new ResponseEntity(accountDTOSalida, headers, HttpStatus.CREATED);
	}

	/*@HystrixCommand(fallbackMethod = "metodoApoyo", threadPoolKey = "AccountThreadPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "500"),
			@HystrixProperty(name = "maxQueueSize", value = "20") }, commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "200"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })*/
	@GetMapping(path = "/{id}")
	public ResponseEntity<AccountDTO> findAccount(@PathVariable("id") Long accountDTOId) {
		System.out.println("findAccount");
		sleep();
		AccountDTO accountDTO = new AccountDTO(null, null, null, accountDTOId);
		accountDTO = accountMapper
				.AccountDAOToAccountDTO(accountService.findAccount(accountMapper.AccountDTOToAccountDAO(accountDTO)));
		System.out.println("accountDTO["+accountDTO+"]");
		return new ResponseEntity(accountDTO, accountDTO != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		//return accountDTO;
	}
	
	@GetMapping(path = "/customer/{id}")
	public ResponseEntity<List<AccountDTO>> findAccountByCustomer(@PathVariable("id") Long IdCustomer){
		AccountDTO accountDTO = new AccountDTO(null, IdCustomer, null, null);
		List<AccountDAO> accountlist = accountService.findAccountByCustomer(accountMapper.AccountDTOToAccountDAO(accountDTO));
		List<AccountDTO> accountDTOList = accountlist.stream()
													 .map(accountDAO -> accountMapper.AccountDAOToAccountDTO(accountDAO))
													 .collect(Collectors.toList());
		System.out.println("accountDTO["+accountDTO+"]");
		return new ResponseEntity(accountDTOList,
				accountDTOList != null && accountDTOList.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<AccountDTO> metodoApoyo(Long AccountDTOId) {
		AccountDTO AccountDTO = new AccountDTO();
		AccountDTO.setType("Nombre de prueba");
		return new ResponseEntity(AccountDTO, HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> findAllAccount() {
		System.out.println("findAllAccount");
		List<AccountDAO> accountDAOList = accountService.findAllAccount();
		List<AccountDTO> accountDTOList = accountDAOList.stream()
				.map(accountDAO -> accountMapper.AccountDAOToAccountDTO(accountDAO)).collect(Collectors.toList());

		return new ResponseEntity(accountDTOList,
				accountDTOList != null && accountDTOList.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Long accountDTOId) {
		System.out.println("deleteAccount");
		AccountDTO AccountDTO = new AccountDTO(null, null, null, accountDTOId);
		Respuesta respuesta = accountService.deleteAccount(accountMapper.AccountDTOToAccountDAO(AccountDTO));
		return new ResponseEntity(respuesta.getCodigo().equals("01") ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") Long accountDTOId,
			@RequestBody AccountDTO accountDTO) {
		System.out.println("updateAccount");
		accountDTO.setId(accountDTOId);
		accountDTO = accountMapper
				.AccountDAOToAccountDTO(accountService.updateAccount(accountMapper.AccountDTOToAccountDAO(accountDTO)));
		return new ResponseEntity(accountDTO, accountDTO != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	private void sleep() {
		Random random = new Random();
		int valor = random.nextInt(3);
		System.out.println("Se dormira [" + valor * 10 + "] milisegundos");
		try {
			Thread.sleep(valor * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
