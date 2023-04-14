package com.bayembacke.product.service;

import com.bayembacke.product.entity.Products;
import com.bayembacke.product.exception.ProductException;
import com.bayembacke.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public void addProduct(Products product) throws ProductException {
        if(ProductValidate(product)){
            product.setPhoto(product.getPhoto());
            productRepository.save(product);
        }else{
            throw new ProductException("Veuillez verifier votre saisie");
        }
    }

    public void updateProduct(Products product) throws ProductException{
        if(!productRepository.existsById(product.getIdProduct())){
            throw new ProductException("produit n'existe pas");
        }
        product.setPhoto(product.getPhoto());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) throws ProductException{
        if(!productRepository.existsById(id)){
            throw new ProductException("produit n'existe pas");
        }
        productRepository.deleteById(id);
    }

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }
    public Products getProductById(Long id){
        return productRepository.findByIdProduct(id);
    }

    private boolean ProductValidate(Products product){
        if(productRepository.findByIdProduct(product.getIdProduct())==null)
            return true;
        else
            return false;
    }

}
