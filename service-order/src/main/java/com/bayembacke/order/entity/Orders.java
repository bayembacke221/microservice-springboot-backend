package com.bayembacke.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long idOrder;
    private Date orderDate;
    private int sellingQuantity;
    private double montant;

    private Long idCustomer;

    private Long idProduct;

    public Orders(Date orderDate, int sellingQuantity, double montant, Long idCustomer, Long idProduct) {
        this.orderDate = orderDate;
        this.sellingQuantity = sellingQuantity;
        this.montant = montant;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }
}
