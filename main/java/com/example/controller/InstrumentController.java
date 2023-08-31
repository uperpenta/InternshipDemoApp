package com.example.controller;


import java.util.List;

import com.example.dto.IssuerDTO;
import com.example.dto.VenueDTO;
import com.example.entity.Issuer;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.dto.InstrumentDTO;
import com.example.service.InstrumentService;


@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public List<InstrumentDTO> getAllInstruments() {
        return instrumentService.getAllInstruments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<InstrumentDTO>> getInstrumentById(@PathVariable(name = "id") long id) {
        List<InstrumentDTO> instrument = instrumentService.getInstrumentById(id);
        return ResponseEntity.ok(instrument);
    }

    @PostMapping
    public ResponseEntity<InstrumentDTO> createInstrument(@RequestBody InstrumentDTO instrumentDTO) {
        InstrumentDTO createdInstrument = instrumentService.createInstrument(instrumentDTO);
        return new ResponseEntity<>(createdInstrument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentDTO> updateInstrument(@PathVariable long id, @RequestBody InstrumentDTO instrumentDTO) {
        InstrumentDTO updatedInstrument = instrumentService.updateInstrument(id, instrumentDTO);
        return ResponseEntity.ok(updatedInstrument);
    }

    @PutMapping("/{instrumentId}/venues/{venueId}")
   public ResponseEntity<InstrumentDTO> addVenueToInstrument(@PathVariable long instrumentId, @PathVariable long venueId) {
        InstrumentDTO instrument = instrumentService.addVenueToInstrument(instrumentId, venueId);
        return ResponseEntity.ok(instrument);
    }
    @DeleteMapping("/{instrumentId}/venues/{venueId}")
    public ResponseEntity<InstrumentDTO> deleteVenueFromInstrument(@PathVariable long instrumentId, @PathVariable long venueId) {
        InstrumentDTO instrument = instrumentService.deleteVenueFromInstrument(instrumentId, venueId);
        return ResponseEntity.ok(instrument);
    }
    @GetMapping("/{instrumentId}/venues")
    public List<VenueDTO> getVenuesByInstrument(@PathVariable long instrumentId) {
        return instrumentService.getVenuesByInstrument(instrumentId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstrument(@PathVariable(name = "id") long id) {
        instrumentService.deleteInstrument(id);
        return ResponseEntity.ok("Instrument with id " + id + " has been deleted.");

    }

    @PutMapping("/{instrumentId}/issuer/{issuerId}")
    public ResponseEntity<InstrumentDTO> addIssuerToInstrument(@PathVariable long instrumentId, @PathVariable long issuerId) {
        InstrumentDTO instrument = instrumentService.addIssuerToInstrument(instrumentId, issuerId);
        return ResponseEntity.ok(instrument);
    }

    @GetMapping("/{instrumentId}/issuer")
    public IssuerDTO getIssuerByInstrument(@PathVariable long instrumentId) {
        return instrumentService.getIssuerByInstrument(instrumentId);
    }

    @DeleteMapping("/{instrumentId}/issuer")
    public ResponseEntity<String> deleteIssuerFromInstrument(@PathVariable long instrumentId) {
        instrumentService.deleteIssuerFromInstrument(instrumentId);
        return ResponseEntity.ok("Issuer has been deleted");
    }
}
