package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument,Long> {
    
}
