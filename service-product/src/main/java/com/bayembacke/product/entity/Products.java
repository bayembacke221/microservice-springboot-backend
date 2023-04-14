package com.bayembacke.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Products  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long idProduct;
    private String designation;
    private String description;
    private double prixUnitaire;
    private String photo;
    private int avalaibleQuantity;

    public Products(String designation,
                    String description,
                    double prixUnitaire,
                    String photo,
                    int avalaibleQuantity) {
        this.designation = designation;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.photo = photo;
        this.avalaibleQuantity = avalaibleQuantity;
    }
}