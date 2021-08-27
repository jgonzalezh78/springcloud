package com.gft.shadows.g1.account.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gft.shadows.g1.account.controller.dto.AccountDTO;
import com.gft.shadows.g1.account.dao.entity.AccountDAO;
import com.gft.shadows.g1.account.dao.entity.Respuesta;

public interface IAccountService {

	AccountDAO createAccount(AccountDAO AccountDAO);	
	AccountDAO findAccount(AccountDAO AccountDAOId);
	List<AccountDAO> findAccountByCustomer(AccountDAO AccountDAOId);
	List<AccountDAO> findAllAccount();
	Respuesta deleteAccount(AccountDAO AccountDAOId);
	AccountDAO updateAccount(AccountDAO AccountDAO);
	
	
}
