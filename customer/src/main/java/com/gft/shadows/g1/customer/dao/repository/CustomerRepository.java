package com.gft.shadows.g1.customer.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gft.shadows.g1.customer.dao.entity.CustomerDAO;


@Repository
public interface CustomerRepository extends CrudRepository<CustomerDAO,Long> {

}
