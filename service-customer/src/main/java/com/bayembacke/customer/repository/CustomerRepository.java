package com.bayembacke.customer.repository;

import com.bayembacke.customer.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
    Customers findByIdCustomer(Long id);
}
