package com.example.techecommerceserver.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comment_id;
    private String comment;
    private Integer rate;
    private String resp_comment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "productId")
    //@JsonIgnore
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cId")
    //@JsonIgnore
    private Customer customer;


    public Comment() {

    }

}
