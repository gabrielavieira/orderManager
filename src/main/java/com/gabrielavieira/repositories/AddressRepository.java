package com.gabrielavieira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielavieira.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
