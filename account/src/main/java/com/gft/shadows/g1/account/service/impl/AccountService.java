package com.gft.shadows.g1.account.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gft.shadows.g1.account.dao.entity.AccountDAO;
import com.gft.shadows.g1.account.dao.entity.Respuesta;
import com.gft.shadows.g1.account.dao.repository.AccountRepository;
import com.gft.shadows.g1.account.service.IAccountService;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private AccountRepository AccountRepository;
	
	
	@Override
	public AccountDAO createAccount(AccountDAO AccountDAO) {
		AccountDAO AccountDAOAlta = null;
		if(AccountDAO != null) {
			AccountDAOAlta = AccountRepository.save(AccountDAO);
		}
		return AccountDAOAlta;
	}

	@Override
	public AccountDAO findAccount(AccountDAO accountDAOId) {
		System.out.println("accountDAOId["+accountDAOId+"]["+accountDAOId.getId()+"]");
		Optional<AccountDAO> accountDAO = null;
		if(accountDAOId != null && accountDAOId.getId()>0) {
			accountDAO = AccountRepository.findById(accountDAOId.getId());
			System.out.println("accountDAO["+accountDAO.toString()+"]");
		}
		return accountDAO != null && accountDAO.isPresent()?accountDAO.get():null;
	}

	public List<AccountDAO> findAccountByCustomer(AccountDAO accountDAOId){
		List<AccountDAO> accountList =  AccountRepository.readAccountDAOByidCustomer(accountDAOId.getIdCustomer());
		return accountList;
	}
	
	@Override
	public List<AccountDAO> findAllAccount() {
		Iterable<AccountDAO> iterator = AccountRepository.findAll();
		List<AccountDAO> AccountDAOList = (List<AccountDAO>)iterator;
		return AccountDAOList;
	}

	@Override
	public Respuesta deleteAccount(AccountDAO AccountDAO) {
		Respuesta respuesta = new Respuesta();
		if(AccountDAO != null && AccountDAO.getId()>0 && AccountRepository.existsById(AccountDAO.getId())) {
			AccountRepository.deleteById(AccountDAO.getId());
			respuesta.setCodigo("01");
			respuesta.setMensaje("Elemento eliminado");
		}else {
			respuesta.setCodigo("02");
			respuesta.setMensaje("Elemento no eliminado");
		}
		return respuesta;
	}

	@Override
	public AccountDAO updateAccount(AccountDAO AccountDAO) {
		AccountDAO AccountDAORecuperado = null;
		if(AccountDAO != null && AccountDAO.getId()>0 && AccountRepository.existsById(AccountDAO.getId())) {
			AccountDAORecuperado = AccountRepository.findById(AccountDAO.getId()).get();
			AccountDAORecuperado.setAmount((!StringUtils.isEmpty(AccountDAO.getAmount())?AccountDAO.getAmount():AccountDAORecuperado.getAmount()));
			AccountDAORecuperado.setIdCustomer((!StringUtils.isEmpty(AccountDAO.getIdCustomer())?AccountDAO.getIdCustomer():AccountDAORecuperado.getIdCustomer()));
			AccountDAORecuperado.setType((!StringUtils.isEmpty(AccountDAO.getType())?AccountDAO.getType():AccountDAORecuperado.getType()));
			AccountDAORecuperado = AccountRepository.save(AccountDAORecuperado);			
		}
		return AccountDAORecuperado;
	}

}
