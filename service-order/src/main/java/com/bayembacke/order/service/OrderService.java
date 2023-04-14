package com.bayembacke.order.service;


import com.bayembacke.order.entity.Customers;
import com.bayembacke.order.entity.Orders;
import com.bayembacke.order.entity.Products;
import com.bayembacke.order.exception.OrderException;
import com.bayembacke.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestServiceGradeToCustomer restServiceGradeToCustomer;

    @Autowired
    RestServiceGradeToProduct restServiceGradeToProduct;

    public void addOrder(Orders orders) throws Exception{
        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer = customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product = productResponseEntity.getBody();

            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });

        ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(orders.getIdCustomer());
        Customers customer = customerResponseEntity.getBody();
        ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(orders.getIdProduct());
        Products product = productResponseEntity.getBody();
        if(orders==null || product==null)
        {
            throw new OrderException("La commande n'existe pas");

        }else {
            orders.setIdCustomer(customer.getIdCustomer());
            orders.setIdProduct(product.getIdProduct());

            orderRepository.save(orders);
        }
    }

    public List<Orders> getAllOrders()
    {
        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer=customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product=productResponseEntity.getBody();
            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });

        return orderRepository.findAll();

    }
    public  double getTotalAmount(Long customerID){
        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer=customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product=productResponseEntity.getBody();
            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });

        double totalAmount;
        ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(customerID);
        Customers customer=customerResponseEntity.getBody();
        totalAmount = orderRepository.
                findAll().
                stream().
                filter(order ->
                        order.getIdCustomer().equals(customer.getIdCustomer())).
                mapToDouble(order ->
                        (order.getMontant() * order.getSellingQuantity())).sum();

        return totalAmount;
    }
    public void deleteOrder(Long id)  throws Exception{

        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer=customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product=productResponseEntity.getBody();
            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });
        if(!orderRepository.existsById(id)){
            throw new OrderException("Cette commande n'existe pas");
        }
        orderRepository.deleteById(id);

    }
    public Orders getOrderById(Long id)
    {
        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer=customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product=productResponseEntity.getBody();
            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });
        return orderRepository.findByIdOrder(id);
    }

    public void updateOrder(Orders orders) throws Exception{
        if(!orderRepository.existsById(orders.getIdOrder())){
            throw new OrderException("La commande n'existe pas");
        }

        Orders orders1=orderRepository.findByIdOrder(orders.getIdOrder());
        orders1.setOrderDate(orders.getOrderDate());
        orders1.setMontant(orders.getMontant());
        orders1.setSellingQuantity(orders.getSellingQuantity());
        ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(orders.getIdCustomer());
        Customers customer=customerResponseEntity.getBody();
        ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(orders.getIdProduct());
        Products product=productResponseEntity.getBody();

        if (customer!=null && product!=null){
            orders1.setIdOrder(orders.getIdOrder());
            orders1.setIdCustomer(orders.getIdCustomer());
            orders1.setIdProduct(orders.getIdProduct());
            orders1.setMontant(orders.getMontant());
            orders1.setOrderDate(orders.getOrderDate());
            orders1.setSellingQuantity(orders.getSellingQuantity());

        }else{
            throw new OrderException("Commande inexistante");
        }

        orderRepository.save(orders1);
    }


    public String getAllOrderProduct(Long id){
        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer=customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product=productResponseEntity.getBody();
            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });
        Products product=new Products();
        Orders order=orderRepository.findByIdOrder(id);
        ResponseEntity<List<Products>> productResponseEntityList=restServiceGradeToProduct.getAllProducts();
        List<Products> listProducts=productResponseEntityList.getBody();

        for (Products products : listProducts) {
            if (products.getIdProduct() == order.getIdProduct()) {
                product = products;
            }

        }

        return "Product : "+product.toString()+" Amount: "+order.getMontant()+" Order Date:" +
                " "+order.getOrderDate()+" Selling Quantity: "+order.getSellingQuantity() ;
    }
    public List<Orders>  getAllOrderCustomer(Long customerID){
        orderRepository.findAll().forEach(order->{
            ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(order.getIdCustomer());
            Customers customer=customerResponseEntity.getBody();
            ResponseEntity<Products> productResponseEntity=restServiceGradeToProduct.getProductById(order.getIdProduct());
            Products product=productResponseEntity.getBody();
            if(customer==null || product==null){
                orderRepository.deleteById(order.getIdOrder());
            }

        });

        List<Orders> listOrders=new ArrayList<>();
        ResponseEntity<Customers> customerResponseEntity=restServiceGradeToCustomer.getCustomerById(customerID);
        Customers customer=customerResponseEntity.getBody();
        orderRepository.findAll().forEach(order->{
            if(order.getIdCustomer().equals(customer.getIdCustomer())){
                listOrders.add(order);
            }
        });



        return listOrders;
    }



}