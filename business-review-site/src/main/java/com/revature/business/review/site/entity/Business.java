package com.revature.business.review.site.entity;


import com.revature.business.review.site.constant.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BUSINESS")
public class Business {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUSINESS_ID")
    private Long id;


    @Column(name = "BUSINESS_NAME")
    private String name;

    @Enumerated
    @Column(name = "BUSINESS_TYPE")
    private BusinessType type;

    @Column(name = "CONTACT_INFO")
    private String contactInfo;

    @Column(name = "WEBSITE_LINK")
    private String webLink;

    @Column(name = "BUSINESS_DESCRIPTION")
    private String description;

    @Column(name = "AVERAGE_RATING")
    private float averageRating;

    @Column(name = "CUSTOMER_COMMENT")
    private String customerComments;


    @OneToOne
    @JoinColumn(name = "business_owner_id",referencedColumnName = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<BusinessReview> businessReviews;




}
