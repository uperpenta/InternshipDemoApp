package com.example.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="instrument")
public class Instrument {

    @Id
    @Column(name="instrument_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
//    @Column(unique = true)
    private String ISIN;
    private String currency;
    private String type;
    private String description;
    private String effectiveDate;
    private String status;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "instruments")
    private List<Venue> venues;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="issuer_id")
    private Issuer issuer;



    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public void deleteVenue(Venue venue){
        venues.remove(venue);
    }


    public void addIssuer(Issuer issuer) {
        this.issuer = issuer;
    }


}
