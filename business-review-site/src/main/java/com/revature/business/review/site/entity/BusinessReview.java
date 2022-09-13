package com.revature.business.review.site.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BUSINESS_REVIEW")
public class BusinessReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customer_id;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "CUSTOMER_COMMENT")
    private String customerComments;


    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Business business;

}
