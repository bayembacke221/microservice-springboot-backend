package com.bayembacke.order.entity;

import lombok.Data;

@Data
public class Customers {
    private Long idCustomer;
    private String nom ;
    private String prenom;
    private String adresse;
    private String telephone;
}
