package com.example.entity;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="issuer")
public class Issuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="issuer_id")
    private long id;
    @Column(unique = true)
    private long LEI;
    private String legalName;
    private String description;

}
