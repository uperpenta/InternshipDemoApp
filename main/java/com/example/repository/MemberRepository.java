package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>, JpaSpecificationExecutor<Member>{
}
