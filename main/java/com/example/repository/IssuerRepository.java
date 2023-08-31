package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Issuer;

public interface IssuerRepository extends JpaRepository<Issuer,Long> {
    
}
