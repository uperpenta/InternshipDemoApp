package com.example.repository;

import com.example.entity.Member;
import org.springframework.data.jpa.domain.Specification;

public interface MemberSpecifications {

    static Specification<Member> withLegalName(String legalName) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("legalName")), "%" + legalName.toLowerCase() + "%");
    }

    static Specification<Member> withDescription(String description) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    static Specification<Member> withAddress(String address) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

    // Add more specifications as needed
}