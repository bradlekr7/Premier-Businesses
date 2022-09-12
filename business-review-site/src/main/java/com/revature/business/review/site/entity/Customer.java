package com.revature.business.review.site.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CUSTOMER")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long id;


    @Column(name = "CUSTOMER_RATING")
    private int rating;

    @Column(name = "CUSTOMER_COMMENT")
    private String comments;
}