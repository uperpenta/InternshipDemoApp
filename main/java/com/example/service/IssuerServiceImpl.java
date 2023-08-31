package com.example.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.IssuerDTO;
import com.example.entity.Issuer;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.IssuerRepository;
import org.springframework.stereotype.Service;

@Service
public class IssuerServiceImpl implements IssuerService {

    private final InstrumentRepository instrumentRepository;
    private final IssuerRepository issuerRepository;

    @Autowired
    public IssuerServiceImpl(InstrumentRepository instrumentRepository, IssuerRepository issuerRepository) {
        super();
        this.instrumentRepository = instrumentRepository;
        this.issuerRepository = issuerRepository;
    }

    private IssuerDTO convertToDTO(Issuer issuer) {
        IssuerDTO issuerDTO = new IssuerDTO();
        issuerDTO.setId(issuer.getId());
        issuerDTO.setLEI(issuer.getLEI());
        issuerDTO.setLegalName(issuer.getLegalName());
        issuerDTO.setDescription(issuer.getDescription());
        return issuerDTO;
    }

    private Issuer convertToEntity(IssuerDTO issuerDTO) {
        Issuer issuer = new Issuer();
        issuer.setId(issuerDTO.getId());
        issuer.setLEI(issuerDTO.getLEI());
        issuer.setLegalName(issuerDTO.getLegalName());
        issuer.setDescription(issuerDTO.getDescription());
        return issuer;
    }

    @Override
    public List<IssuerDTO> getAllIssuers() {
        List<Issuer> issuers = issuerRepository.findAll();
        return issuers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IssuerDTO createIssuer(IssuerDTO issuerDTO) {
        Issuer issuerRequest = convertToEntity(issuerDTO);
        Issuer createdIssuer = issuerRepository.save(issuerRequest);
        return convertToDTO(createdIssuer);
    }

    @Override
    public IssuerDTO updateIssuer(long id, IssuerDTO issuerDTO) {
        Issuer issuerRequest = convertToEntity(issuerDTO);
        Issuer issuer = issuerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issuer", id));

        issuer.setLEI(issuerRequest.getLEI());
        issuer.setLegalName(issuerRequest.getLegalName());
        issuer.setDescription(issuerRequest.getDescription());

        issuerRepository.save(issuer);
        return convertToDTO(issuer);
    }

    @Override
    public void deleteIssuer(long id) {
        Issuer issuer = issuerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issuer", id));

        issuerRepository.delete(issuer);
    }

    public List<IssuerDTO> getIssuerById(long id) {
        Optional<Issuer> result = issuerRepository.findById(id);
        if (result.isPresent()) {

            return result.stream().map(this::convertToDTO).collect(Collectors.toList());

        } else {
            throw new ResourceNotFoundException("Member", id);
        }

    }

}
