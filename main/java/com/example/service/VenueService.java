package com.example.service;

import java.util.List;

import com.example.dto.InstrumentDTO;
import com.example.dto.VenueDTO;
import com.example.entity.Venue;

public interface VenueService {
    List<VenueDTO> getAllVenues();

    VenueDTO createVenue(VenueDTO venueDTO);

    VenueDTO updateVenue(long id, VenueDTO venueDTO);

    void deleteVenue(long id);

    List<VenueDTO> getVenueById(long id);

    VenueDTO addInstrumentToVenue(long instrumentId, long venueId);

    VenueDTO deleteInstrumentFromVenue(long instrumentId, long venueId);

    List<InstrumentDTO> getInstrumentsByVenue(long venueId);


}
