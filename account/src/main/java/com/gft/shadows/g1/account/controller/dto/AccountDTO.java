package com.gft.shadows.g1.account.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

	private String type;
	private Long idCustomer;
	private Double amount;
	private Long id;
	
}
