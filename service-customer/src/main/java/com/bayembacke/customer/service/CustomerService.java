package com.bayembacke.customer.service;

import com.bayembacke.customer.entity.Customers;
import com.bayembacke.customer.exception.CustomerException;
import com.bayembacke.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void addCustomer(Customers customer) throws CustomerException {
        if(CustomerValidate(customer)){
            customerRepository.save(customer);
        }else{
            throw new CustomerException("Veuillez verifier votre saisie");
        }
    }

    public void updateCustomer(Customers customer) throws CustomerException{
        if(customer==null){
            throw new CustomerException("Le client n'est pas inscrit");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) throws CustomerException{
        if(customerRepository.findByIdCustomer(id)==null){
            throw new CustomerException("client n'existe pas");
        }
        customerRepository.deleteById(id);
    }

    public List<Customers> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customers getCustomerById(Long id){
        return customerRepository.findByIdCustomer(id);
    }

    private boolean CustomerValidate(Customers customer){
        if(customerRepository.findByIdCustomer(customer.getIdCustomer())==null)
            return true;
        else
            return false;
    }
}
