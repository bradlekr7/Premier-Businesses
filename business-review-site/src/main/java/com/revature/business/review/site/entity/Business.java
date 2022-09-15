package com.revature.business.review.site.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BUSINESS")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessId;

    @Column(name = "BUSINESS_NAME")
    private String businessName;

    //@Enumerated
    @Column(name = "BUSINESS_TYPE")
    //private BusinessType businessType;
    private String businessType;

    @Column(name = "CONTACT_INFO")
    private String businessContactInfo;

    @Column(name = "WEBSITE_LINK")
    private String webLink;

    @Column(name = "BUSINESS_DESCRIPTION")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private User user;

}
