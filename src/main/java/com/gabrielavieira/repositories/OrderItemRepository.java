package com.gabrielavieira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielavieira.domain.OrderItem;
import com.gabrielavieira.domain.OrderItemPK;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
