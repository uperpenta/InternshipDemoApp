package com.example.service;

import java.util.List;

import com.example.dto.InstrumentDTO;
import com.example.dto.IssuerDTO;
import com.example.dto.VenueDTO;
import com.example.entity.Issuer;

public interface InstrumentService {
    List<InstrumentDTO> getAllInstruments();

    InstrumentDTO createInstrument(InstrumentDTO instrumentDTO);

    InstrumentDTO updateInstrument(long id, InstrumentDTO instrumentDTO);

    void deleteInstrument(long id);

    List<InstrumentDTO> getInstrumentById(long id);


    InstrumentDTO addVenueToInstrument(long instrumentId, long venueId);

    InstrumentDTO deleteVenueFromInstrument(long instrumentId, long venueId);

    List<VenueDTO> getVenuesByInstrument(long instrumentId);


    InstrumentDTO addIssuerToInstrument(long instrumentId, long issuerId);

    void deleteIssuerFromInstrument(long instrumentId);

    IssuerDTO getIssuerByInstrument(long instrumentId);

}
