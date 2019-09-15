package com.gabrielavieira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielavieira.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
