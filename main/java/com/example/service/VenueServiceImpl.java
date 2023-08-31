package com.example.service;

import com.example.dto.InstrumentDTO;
import com.example.entity.Instrument;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.repository.VenueRepository;
import com.example.dto.VenueDTO;
import com.example.entity.Venue;
import com.example.exception.ResourceNotFoundException;

@Service
public class VenueServiceImpl implements VenueService {

    private final InstrumentRepository instrumentRepository;
    @Autowired
    private final VenueRepository venueRepository;

    public VenueServiceImpl(InstrumentRepository instrumentRepository, VenueRepository venueRepository) {
        this.instrumentRepository = instrumentRepository;
        this.venueRepository = venueRepository;
    }

    private VenueDTO convertToDTO(Venue venue) {
        VenueDTO venueDTO = new VenueDTO();
        venueDTO.setCity(venue.getCity());
        venueDTO.setCountry(venue.getCountry());
        venueDTO.setName(venue.getName());
        venueDTO.setId(venue.getId());
        return venueDTO;
    }

    @Override
    public List<VenueDTO> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();
        return venues.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public VenueDTO createVenue(VenueDTO venueDTO) {
        Venue venueRequest = convertToEntity(venueDTO);
        Venue createdVenue = venueRepository.save(venueRequest);
        return convertToDTO(createdVenue);
    }

    @Override
    public VenueDTO updateVenue(long id, VenueDTO venueDTO) {
        Venue venueRequest = convertToEntity(venueDTO);
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", id));

        venue.setCity(venueRequest.getCity());
        venue.setCountry(venueRequest.getCountry());
        venue.setName(venueRequest.getName());

        Venue updatedVenue = venueRepository.save(venue);
        return convertToDTO(updatedVenue);
    }

    @Override
    public void deleteVenue(long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", id));

        venueRepository.delete(venue);
    }

    public List<VenueDTO> getVenueById(long id) {
   Optional<Venue> result = venueRepository.findById(id);
        if (result.isPresent()) {

            return result.stream().map(this::convertToDTO).collect(Collectors.toList());

        } else {
            throw new ResourceNotFoundException("Member", id);
        }
    }
    

    private Venue convertToEntity(VenueDTO venueDTO) {
        Venue venue = new Venue();
        venue.setCity(venueDTO.getCity());
        venue.setCountry(venueDTO.getCountry());
        venue.setName(venueDTO.getName());
        venue.setId(venueDTO.getId());
        return venue;
    }


    public VenueDTO addInstrumentToVenue(long instrumentId, long venueId) {
        Instrument existingInstrument = instrumentRepository.findById(instrumentId).orElse(null);

        if (existingInstrument == null) {
            throw new ResourceNotFoundException("Instrument", instrumentId);
        }

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", venueId));

        if (venue.getInstruments().contains(existingInstrument)) {
            throw new ResourceAlreadyExistsException("Instrument");
        }

        existingInstrument.addVenue(venue);
        venue.addInstrument(existingInstrument);

        instrumentRepository.save(existingInstrument);
        venueRepository.save(venue);

        return convertToDTO(venue);
    }



    public VenueDTO deleteInstrumentFromVenue(long venueId, long instrumentId){
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", venueId));

        Instrument instrument = instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));

        venue.deleteInstrument(instrument);
        venueRepository.save(venue);

        return convertToDTO(venue);
    }


    public List<InstrumentDTO> getInstrumentsByVenue(long venueId) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", venueId));

        return venue.getInstruments().stream().map(instrument -> {
            InstrumentDTO instrumentDTO = new InstrumentDTO();
            instrumentDTO.setId(instrument.getId());
            instrumentDTO.setType(instrument.getType());
            instrumentDTO.setISIN(instrument.getISIN());
            instrumentDTO.setCurrency(instrument.getCurrency());
            instrumentDTO.setStatus(instrument.getStatus());
            instrumentDTO.setDescription(instrument.getDescription());
            instrumentDTO.setEffectiveDate(instrument.getEffectiveDate());
            return instrumentDTO;
        }).collect(Collectors.toList());
    }
}
