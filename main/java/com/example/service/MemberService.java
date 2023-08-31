package com.example.service;

import java.util.List;

import com.example.dto.MemberDTO;
import com.example.dto.VenueDTO;


public interface MemberService {
    List<MemberDTO> getAllMembers();

    MemberDTO createMember(MemberDTO memberDTO);

    MemberDTO updateMember(long id, MemberDTO memberDTO);

    void deleteMember(long id);

    MemberDTO getMemberById(long id);

    MemberDTO addVenueToMember(long memberId, long venueId);

    void deleteVenueFromMember(long memberId);

    VenueDTO getVenueByMember(long memberId);

    List<MemberDTO> findMembersByFields(String legalName, String description, String address);
}
