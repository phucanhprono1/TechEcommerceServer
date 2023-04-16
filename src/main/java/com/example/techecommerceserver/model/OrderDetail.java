package com.example.techecommerceserver.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private int numberSell;
}
