package com.example.OjekOnline.service;

import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.model.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public ResponseEntity<?> createMember( Member member);

    public Member findMemberById(int id);

    public List<Member> findAll();

    public ResponseEntity<?> deleteMember(int id);

    public ResponseEntity<?> updateMemberBalance(int id, double balance, boolean bool);

    public ResponseEntity<?> updateMemberProfile(int id, Member Member);
}
