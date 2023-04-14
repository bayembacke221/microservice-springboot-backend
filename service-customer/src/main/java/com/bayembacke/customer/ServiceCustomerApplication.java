package com.bayembacke.customer;

import com.bayembacke.customer.entity.Customers;
import com.bayembacke.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceCustomerApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomerApplication.class, args);
    }
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customers("Modou","Faye","hhddh","231131"));
        customerRepository.save(new Customers("Khadim","Diom","dekfe","48103"));
        customerRepository.save(new Customers("Ibrahim","Diouf","ncdnfefen","248211"));
        customerRepository.save(new Customers("Mor","Mbengue","fmroigr","98082824"));
        customerRepository.save(new Customers("Cheikh","Thioune","nvnrorfigugt","8289428"));
    }
}
