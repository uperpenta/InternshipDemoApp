package com.example.service;


import com.example.dto.MemberDTO;
import com.example.dto.VenueDTO;
import com.example.entity.Member;
import com.example.entity.Venue;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.MemberRepository;
import com.example.repository.MemberSpecifications;
import com.example.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final VenueRepository venueRepository;


    public MemberServiceImpl(MemberRepository memberRepository, VenueRepository venueRepository) {
        super();
        this.memberRepository = memberRepository;
        this.venueRepository = venueRepository;
    }

    public MemberDTO convertToDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setAddress(member.getAddress());
        memberDTO.setDescription(member.getDescription());
        memberDTO.setLEI(member.getLEI());
        memberDTO.setLegalName(member.getLegalName());
        memberDTO.setId(member.getId());

        return memberDTO;

    }

    private Member convertToEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setAddress(memberDTO.getAddress());
        member.setDescription(memberDTO.getDescription());
        member.setLEI(memberDTO.getLEI());
        member.setLegalName(memberDTO.getLegalName());
        member.setId(memberDTO.getId());

        return member;
    }


    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {

        Member memberRequest = convertToEntity(memberDTO);

        Member createdMember = memberRepository.save(memberRequest);

        return convertToDTO(createdMember);
    }

    @Override
    public MemberDTO updateMember(long id, MemberDTO memberDTO) {
        Member memberRequest = convertToEntity(memberDTO);
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", id));

        member.setAddress(memberRequest.getAddress());
        member.setDescription(memberRequest.getDescription());
        member.setLEI(memberRequest.getLEI());
        member.setLegalName(memberRequest.getLegalName());


        memberRepository.save(member);

        return convertToDTO(member);

    }

    @Override
    public void deleteMember(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", id));

        memberRepository.delete(member);

    }

    @Override
    public MemberDTO getMemberById(long id) {


        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {

            return convertToDTO(result.get());

        } else {
            throw new ResourceNotFoundException("Member", id);
        }
    }

    @Override
    public MemberDTO addVenueToMember(long id, long venueId) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", id));

        Venue venue = venueRepository.findById(venueId).orElseThrow(() -> new ResourceNotFoundException("Venue", venueId));

        member.addVenue(venue);
        memberRepository.save(member);
        return convertToDTO(member);
    }

    @Override
    public void deleteVenueFromMember(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", id));
        member.setVenue(null);
        memberRepository.save(member);
    }

    @Override
    public VenueDTO getVenueByMember(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", id));

        if (member.getVenue() == null) {
            throw new ResourceNotFoundException("Venue", id);
        }

        Venue venue = member.getVenue();
        VenueDTO venueDTO = new VenueDTO();
        venueDTO.setId(venue.getId());
        venueDTO.setName(venue.getName());
        venueDTO.setCity(venue.getCity());
        venueDTO.setCountry(venue.getCountry());

        return venueDTO;
    }

    @Override
    public List<MemberDTO> findMembersByFields(String legalName, String description, String address) {
        Specification<Member> spec = Specification.where(null);

        if (!ObjectUtils.isEmpty(legalName)) {
            spec = spec.and(MemberSpecifications.withLegalName(legalName));
        }

        if (!ObjectUtils.isEmpty(description)) {
            spec = spec.and(MemberSpecifications.withDescription(description));
        }

        if (!ObjectUtils.isEmpty(address)) {
            spec = spec.and(MemberSpecifications.withAddress(address));
        }

        List<Member> members = memberRepository.findAll(spec);

        return members.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}
