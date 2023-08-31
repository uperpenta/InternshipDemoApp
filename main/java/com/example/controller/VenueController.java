package com.example.controller;

import java.util.List;

import com.example.dto.InstrumentDTO;
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


import com.example.dto.VenueDTO;
import com.example.service.VenueService;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public List<VenueDTO> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<VenueDTO>> getVenueById(@PathVariable(name = "id") long id) {
        List<VenueDTO> venue = venueService.getVenueById(id);
        return ResponseEntity.ok(venue);
    }

    @PostMapping
    public ResponseEntity<VenueDTO> createVenue(@RequestBody VenueDTO venueDTO) {

        VenueDTO createdVenue = venueService.createVenue(venueDTO);

        return new ResponseEntity<>(createdVenue, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> updateVenue(@PathVariable long id, @RequestBody VenueDTO venueDTO) {
        VenueDTO updatedVenue = venueService.updateVenue(id, venueDTO);
        return ResponseEntity.ok(updatedVenue);
    }

    @PutMapping("/{venueId}/instruments/{instrumentId}")
    public ResponseEntity<VenueDTO> addInstrumentToVenue(@PathVariable long instrumentId, @PathVariable long venueId) {
        VenueDTO venue = venueService.addInstrumentToVenue(instrumentId, venueId);
        return ResponseEntity.ok(venue);
    }

    @DeleteMapping("/{venueId}/instruments/{instrumentId}")
    public ResponseEntity<VenueDTO> deleteInstrumentFromVenue(@PathVariable long instrumentId, @PathVariable long venueId) {
        VenueDTO venue = venueService.deleteInstrumentFromVenue(instrumentId, venueId);
        return ResponseEntity.ok(venue);
    }

    @GetMapping("/{venueId}/instruments")
    public List<InstrumentDTO> getInstrumentsByVenue(@PathVariable long venueId) {
        return venueService.getInstrumentsByVenue(venueId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable(name = "id") long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.ok("Venue with id " + id + " has been deleted.");
    }

}