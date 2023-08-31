package com.example.service;

import com.example.dto.IssuerDTO;
import com.example.dto.VenueDTO;
import com.example.entity.Issuer;
import com.example.entity.Venue;
import com.example.repository.IssuerRepository;
import com.example.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dto.InstrumentDTO;
import com.example.entity.Instrument;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.InstrumentRepository;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final VenueRepository venueRepository;
    private final InstrumentRepository instrumentRepository;

    private final IssuerRepository issuerRepository;

    @Autowired
    public InstrumentServiceImpl(VenueRepository venueRepository, InstrumentRepository instrumentRepository, IssuerRepository issuerRepository) {
        this.venueRepository = venueRepository;
        this.instrumentRepository = instrumentRepository;
        this.issuerRepository = issuerRepository;
    }

    private InstrumentDTO convertToDTO(Instrument instrument) {
        InstrumentDTO instrumentDTO = new InstrumentDTO();
        instrumentDTO.setId(instrument.getId());
        instrumentDTO.setISIN(instrument.getISIN());
        instrumentDTO.setCurrency(instrument.getCurrency());
        instrumentDTO.setType(instrument.getType());
        instrumentDTO.setDescription(instrument.getDescription());
        instrumentDTO.setEffectiveDate(instrument.getEffectiveDate());
        instrumentDTO.setStatus(instrument.getStatus());
        return instrumentDTO;
    }

    private Instrument convertToEntity(InstrumentDTO instrumentDTO) {
        Instrument instrument = new Instrument();
        instrument.setId(instrumentDTO.getId());
        instrument.setISIN(instrumentDTO.getISIN());
        instrument.setCurrency(instrumentDTO.getCurrency());
        instrument.setType(instrumentDTO.getType());
        instrument.setDescription(instrumentDTO.getDescription());
        instrument.setEffectiveDate(instrumentDTO.getEffectiveDate());
        instrument.setStatus(instrumentDTO.getStatus());
        return instrument;
    }

    @Override
    public List<InstrumentDTO> getAllInstruments() {
        List<Instrument> instruments = instrumentRepository.findAll();
        return instruments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public InstrumentDTO createInstrument(InstrumentDTO instrumentDTO) {
        Instrument instrumentRequest = convertToEntity(instrumentDTO);
        Instrument createdInstrument = instrumentRepository.save(instrumentRequest);
        return convertToDTO(createdInstrument);
    }

    @Override
    public InstrumentDTO updateInstrument(long id, InstrumentDTO instrumentDTO) {
        Instrument instrumentRequest = convertToEntity(instrumentDTO);
        Instrument instrument = instrumentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Instrument", id));

        instrument.setISIN(instrumentRequest.getISIN());
        instrument.setCurrency(instrumentRequest.getCurrency());
        instrument.setType(instrumentRequest.getType());
        instrument.setDescription(instrumentRequest.getDescription());
        instrument.setEffectiveDate(instrumentRequest.getEffectiveDate());
        instrument.setStatus(instrumentRequest.getStatus());

        instrumentRepository.save(instrument);
        return convertToDTO(instrument);
    }

    @Override
    public void deleteInstrument(long id) {
        Instrument instrument = instrumentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Instrument", id));

        instrumentRepository.delete(instrument);
    }

    @Override
    public List<InstrumentDTO> getInstrumentById(long id) {
        Optional<Instrument> result = instrumentRepository.findById(id);
        if (result.isPresent()) {

            return result.stream().map(this::convertToDTO).collect(Collectors.toList());

        }
        return null;
    }

    @Override
    public InstrumentDTO addVenueToInstrument(long instrumentId, long venueId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(() -> new ResourceNotFoundException("Venue", venueId));

        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));


        instrument.addVenue(venue);
        venue.addInstrument(instrument);
        instrumentRepository.save(instrument);
        venueRepository.save(venue);

        return convertToDTO(instrument);
    }

    @Override
    public InstrumentDTO deleteVenueFromInstrument(long instrumentId, long venueId) {
        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));

        Venue venue = venueRepository.findById(venueId).orElseThrow(() -> new ResourceNotFoundException("Venue", venueId));

        instrument.deleteVenue(venue);
        instrumentRepository.save(instrument);

        return convertToDTO(instrument);
    }

    @Override
    public List<VenueDTO> getVenuesByInstrument(long instrumentId) {
        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));


        return instrument.getVenues().stream().map(venue -> {
            VenueDTO venueDTO = new VenueDTO();
            venueDTO.setId(venue.getId());
            venueDTO.setName(venue.getName());
            venueDTO.setCity(venue.getCity());
            venueDTO.setCountry(venue.getCountry());
            return venueDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public InstrumentDTO addIssuerToInstrument(long instrumentId, long issuerId) {
        Issuer issuer = issuerRepository.findById(issuerId).orElseThrow(() -> new ResourceNotFoundException("Issuer", issuerId));

        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));

        instrument.addIssuer(issuer);

        instrumentRepository.save(instrument);

        return convertToDTO(instrument);
    }

    @Override
    public void deleteIssuerFromInstrument(long instrumentId) {
        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));

        instrument.setIssuer(null);
        instrumentRepository.save(instrument);
    }

    @Override
    public IssuerDTO getIssuerByInstrument(long instrumentId) {
        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", instrumentId));

        if (instrument.getIssuer() == null) {
            throw new ResourceNotFoundException("Issuer", instrumentId);
        }

        Issuer issuer = instrument.getIssuer();

        IssuerDTO issuerDTO = new IssuerDTO();
        issuerDTO.setId(issuer.getId());
        issuerDTO.setLEI(issuer.getLEI());
        issuerDTO.setLegalName(issuer.getLegalName());
        issuerDTO.setDescription(issuer.getDescription());

        return issuerDTO;

    }
}
