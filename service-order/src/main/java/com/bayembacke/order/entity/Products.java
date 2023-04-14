package com.bayembacke.order.entity;

import lombok.Data;

@Data
public class Products {
    private Long idProduct;
    private String designation;
    private String description;
    private double prixUnitaire;
    private String photo;
    private int avalaibleQuantity;
}
