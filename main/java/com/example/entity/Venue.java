package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="venue")
public class Venue {

    
    @Id
    @Column(name="venue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String country;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "venue_instrument",
            joinColumns = @JoinColumn(name = "venue_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id"))
    private List<Instrument> instruments;

    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);

    }

    public void deleteInstrument(Instrument instrument){
        instruments.remove(instrument);
    }
}
