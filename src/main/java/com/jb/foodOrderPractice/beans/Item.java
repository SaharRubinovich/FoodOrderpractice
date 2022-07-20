package com.jb.foodOrderPractice.beans;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "company_id")
    private long companyId;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private double price;
    private String image;
}
