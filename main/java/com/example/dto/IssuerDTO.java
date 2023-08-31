package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuerDTO {
    private long id;
    private long LEI;
    private String legalName;
    private String description;
}
