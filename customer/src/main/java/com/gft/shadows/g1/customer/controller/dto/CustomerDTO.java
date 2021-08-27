package com.gft.shadows.g1.customer.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private String name;
	private String nss;
	private String birthDate;
	private String genero;
	private Long id;
	
}
