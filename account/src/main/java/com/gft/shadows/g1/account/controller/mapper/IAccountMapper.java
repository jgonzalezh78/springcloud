package com.gft.shadows.g1.account.controller.mapper;

import org.mapstruct.Mapper;

import com.gft.shadows.g1.account.controller.dto.AccountDTO;
import com.gft.shadows.g1.account.dao.entity.AccountDAO;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

	AccountDAO AccountDTOToAccountDAO(AccountDTO AccountDTO);
	AccountDTO AccountDAOToAccountDTO(AccountDAO AccountDAO );
		
}
