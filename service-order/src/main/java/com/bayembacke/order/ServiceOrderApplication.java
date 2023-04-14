package com.bayembacke.order;

import com.bayembacke.order.entity.Orders;
import com.bayembacke.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class ServiceOrderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
    @Autowired
    OrderRepository orderRepository;
    @Override
    public void run(String... args) throws Exception {
        orderRepository.save(new Orders(
                new Date(),
                2,
                123,
                1L,
                2L));
        orderRepository.save(new Orders(
                new Date(),
                22,
                1234,
                2L,
                1L));
        orderRepository.save(new Orders(
                new Date(),
                2,
                123.0,
                4L,
                3L));
    }
}
