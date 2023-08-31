package com.example.controller;

import java.util.List;

import com.example.dto.VenueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins= "http://localhost:8080/api/members")
public class MemberController {


    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable(name = "id") long id) {
        MemberDTO member = memberService.getMemberById(id);
        
        return ResponseEntity.ok().body(member);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember=memberService.createMember(memberDTO);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
 
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable long id, @RequestBody MemberDTO memberDTO) {

        MemberDTO member = memberService.updateMember(id, memberDTO);

        return ResponseEntity.ok().body(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable(name = "id") long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member with id" + id + " has been deleted.");
    }

    @PutMapping("/{memberId}/venue/{venueId}")
    public ResponseEntity<MemberDTO> addVenueToMember(@PathVariable long memberId, @PathVariable long venueId) {
        MemberDTO member = memberService.addVenueToMember(memberId, venueId);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{memberId}/venue")
    public ResponseEntity<String> deleteVenueFromMember(@PathVariable long memberId) {
        memberService.deleteVenueFromMember(memberId);
        return ResponseEntity.ok("Venue has been removed");
    }

    @GetMapping("/{memberId}/venue")
    public VenueDTO getVenueByMember(@PathVariable long memberId) {
        return memberService.getVenueByMember(memberId);
    }


    @GetMapping("/search")
    public List<MemberDTO> searchMembers(
            @RequestParam(name = "legalName", required = false) String legalName,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "address", required = false) String address
    ) {
        return memberService.findMembersByFields(legalName, description, address);
    }

}
