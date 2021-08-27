package com.gft.shadows.g1.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gft.shadows.g1.account.controller.dto.AccountDTO;

public interface IAccount {

	ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO);	
	ResponseEntity<AccountDTO> findAccount(Long accountDTOId);
	ResponseEntity<List<AccountDTO>> findAccountByCustomer(Long IdCustomer);
	ResponseEntity<List<AccountDTO>> findAllAccount();
	ResponseEntity<HttpStatus>deleteAccount(Long accountDTOId);
	ResponseEntity<AccountDTO> updateAccount(Long accountDTOId,AccountDTO accountDTO);
	
}
