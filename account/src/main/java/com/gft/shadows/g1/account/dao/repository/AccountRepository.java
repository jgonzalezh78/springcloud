package com.gft.shadows.g1.account.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gft.shadows.g1.account.dao.entity.AccountDAO;


@Repository
public interface AccountRepository extends CrudRepository<AccountDAO,Long> {
		List<AccountDAO> readAccountDAOByidCustomer(Long id);
}
