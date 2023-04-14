package com.bayembacke.order.service;

import com.bayembacke.order.entity.Customers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "service-customer",url = "http://localhost:8081/customer")
public interface RestServiceGradeToCustomer {
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    ResponseEntity<Customers> getCustomerById(@PathVariable("id") Long id);

    @RequestMapping(method =RequestMethod.GET,value ="/all")
    ResponseEntity<List<Customers>> getAllCustomers();
}