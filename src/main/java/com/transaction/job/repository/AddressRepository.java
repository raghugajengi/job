package com.transaction.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.transaction.job.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}