package com.example.OjekOnline.controller;

import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.model.Member;
import com.example.OjekOnline.service.MemberService;
import com.example.OjekOnline.service.ServiceImpl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> findAllMembers(){
        return memberService.findAll();
    }

    @GetMapping("/members/{id}")
    public Member findMemberById(@PathVariable("id") int id){
        return memberService.findMemberById(id);
    }

    @PostMapping("/members")
    public ResponseEntity<?> createNewMember(@RequestBody  Member member){
        return memberService.createMember(member);
    }
    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") int id){
        return memberService.deleteMember(id);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") int id,@RequestBody Member member){
        return memberService.updateMemberProfile(id,member);
    }
}
