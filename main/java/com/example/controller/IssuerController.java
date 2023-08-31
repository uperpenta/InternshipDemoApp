package com.example.controller;

import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.IssuerDTO;
import com.example.service.IssuerService;


@RestController
@RequestMapping("/api/issuers")
public class IssuerController {

    private final IssuerService issuerService;




    public IssuerController(IssuerService issuerService) {
        super();
        this.issuerService = issuerService;
    }

    @GetMapping
    public List<IssuerDTO> getAllIssuers() {
        return issuerService.getAllIssuers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<IssuerDTO>> getIssuerById(@PathVariable(name = "id") long id) {
        List<IssuerDTO> issuer = issuerService.getIssuerById(id);
        return ResponseEntity.ok(issuer);
    }

    @PostMapping
    public ResponseEntity<IssuerDTO> createIssuer(@RequestBody IssuerDTO issuerDTO) {
        IssuerDTO createdIssuer = issuerService.createIssuer(issuerDTO);
        return new ResponseEntity<>(createdIssuer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssuerDTO> updateIssuer(@PathVariable long id, @RequestBody IssuerDTO issuerDTO) {
        IssuerDTO updatedIssuer = issuerService.updateIssuer(id, issuerDTO);
        return ResponseEntity.ok(updatedIssuer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssuer(@PathVariable(name = "id") long id) {
        issuerService.deleteIssuer(id);
        return ResponseEntity.ok("Issuer with id " + id + " has been deleted.");
    }

}

