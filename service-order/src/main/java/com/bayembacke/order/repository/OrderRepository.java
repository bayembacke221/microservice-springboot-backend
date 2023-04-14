package com.bayembacke.order.repository;

import com.bayembacke.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    Orders findByIdOrder(Long id);
}
