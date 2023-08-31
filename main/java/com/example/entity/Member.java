package com.example.entity;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private long id;
    @Column(unique = true)
    private String LEI;
    private String legalName;
    private String description;
    private String address;
    
    @JoinColumn(name="venue_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Venue venue;


    public void addVenue(Venue venue) {
        this.venue = venue;
    }
}
