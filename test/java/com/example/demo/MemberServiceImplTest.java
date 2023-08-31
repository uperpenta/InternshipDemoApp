package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.dto.MemberDTO;
import com.example.entity.Member;

import com.example.repository.MemberRepository;
import com.example.service.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MemberServiceImplTest {

   @Mock
    private MemberRepository memberRepository;

   @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateMember(){
        MemberDTO actualMemberDTO = new MemberDTO();
        actualMemberDTO.setLEI("LEI123");
        actualMemberDTO.setLegalName("LegalName123");
        actualMemberDTO.setDescription("Description123");
        actualMemberDTO.setAddress("Address123");

        Member expectedMemberEntity = new Member();
        expectedMemberEntity.setLEI("LEI123");
        expectedMemberEntity.setLegalName("LegalName123");
        expectedMemberEntity.setDescription("Description123");
        expectedMemberEntity.setAddress("Address123");
        expectedMemberEntity.setId(1L);

        when(memberRepository.save(any(Member.class))).thenReturn(expectedMemberEntity);

        MemberDTO resultDTO = memberService.createMember(actualMemberDTO);

        verify(memberRepository).save(any(Member.class));

        assertEquals(actualMemberDTO.getLEI(), resultDTO.getLEI());
        assertEquals(actualMemberDTO.getLegalName(), resultDTO.getLegalName());
        assertEquals(actualMemberDTO.getDescription(), resultDTO.getDescription());
        assertEquals(actualMemberDTO.getAddress(), resultDTO.getAddress());

        assertEquals(expectedMemberEntity.getId(), resultDTO.getId());
    }

//    @Test
//    public void testUpdateMember(){
//
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setLEI("LEI123");
//        memberDTO.setLegalName("LegalName123");
//        memberDTO.setDescription("Description123");
//        memberDTO.setAddress("Address123");
//
//        Member member = new Member();
//        member.setLEI("LEI123");
//        member.setLegalName("LegalName123");
//        member.setDescription("Description123");
//        member.setAddress("Address123");
//        member.setId(1L);
//
//
//        //final MemberDTO memberDTO=mock(MemberDTO.class);
//
//        final String updatedLegalName="Updated Legal Name";
//
//        //final Member member=mock(Member.class);
//
//        when(memberRepository.findById(1L)).thenReturn(java.util.Optional.of(member));
//
//        when(memberRepository.save(any(Member.class))).thenReturn(member);
//
//        when(memberDTO.getLegalName()).thenReturn(updatedLegalName);
//
//        System.out.println(memberDTO.getLegalName());
//        System.out.println(member.getId());
//
//        final MemberDTO result=memberService.updateMember(1L, memberDTO);
//
//        Mockito.verify(member).setLegalName("Updated Legal Name");
//
//        System.out.println(result.getLegalName());
//
//        Mockito.verify(memberRepository).save(member);
//
//        String resultLegalName=result.getLegalName();
//
//        assertEquals(updatedLegalName, resultLegalName);
//
//    }

}
