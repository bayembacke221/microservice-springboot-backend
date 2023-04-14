package com.bayembacke.order.resource;


import com.bayembacke.order.entity.Orders;
import com.bayembacke.order.exception.OrderException;
import com.bayembacke.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity addOrder (@RequestBody Orders orders) throws Exception{

        orderService.addOrder(orders);

        return  new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders()
    {
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrders(@PathVariable("id") Long id) throws Exception {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrdersById(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
    }
    @PutMapping
    public  ResponseEntity update(@RequestBody Orders orders) throws Exception
    {
        orderService.updateOrder(orders);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/order-list-product/{id}")
    public ResponseEntity<String> getAllOrderProduct(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(orderService.getAllOrderProduct(id),HttpStatus.OK);
    }

    @GetMapping("/order-list-customer/{id}")
    public ResponseEntity<List<Orders>> getAllOrderCustomer(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(orderService.getAllOrderCustomer(id),HttpStatus.OK);
    }
    @GetMapping("/amount/{id}")
    public ResponseEntity<Double> getTotalAmount(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(orderService.getTotalAmount(id),HttpStatus.OK);
    }
}