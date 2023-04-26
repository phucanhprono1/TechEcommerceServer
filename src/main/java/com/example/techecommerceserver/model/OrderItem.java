package com.example.techecommerceserver.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private double price;
    @ManyToOne
    private Orders orders;
}