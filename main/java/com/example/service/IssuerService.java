package com.example.service;

import java.util.List;

import com.example.dto.InstrumentDTO;
import com.example.dto.IssuerDTO;

public interface IssuerService {
    List<IssuerDTO> getAllIssuers();

    IssuerDTO createIssuer(IssuerDTO issuerDTO);

    IssuerDTO updateIssuer(long id, IssuerDTO issuerDTO);

    void deleteIssuer(long id);

    List<IssuerDTO> getIssuerById(long id);

}
