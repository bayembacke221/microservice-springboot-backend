package com.bayembacke.order.service;

import com.bayembacke.order.entity.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "http://localhost:8082/produit",name = "service-product")
public interface RestServiceGradeToProduct {
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    ResponseEntity<Products> getProductById(@PathVariable("id") Long id);

    @RequestMapping(method =RequestMethod.GET,value ="/all" )
    ResponseEntity<List<Products>> getAllProducts();
}