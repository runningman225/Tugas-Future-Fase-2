package com.example.OjekOnline.service.ServiceImpl;

import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.model.Member;
import com.example.OjekOnline.repository.DriverRepository;
import com.example.OjekOnline.repository.MemberRepository;
import com.example.OjekOnline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public ResponseEntity<?> createMember(Member member) {
        Member memberExist = memberRepository.findById(member.getId());
        if(memberExist==null) {
            memberRepository.save(member);
            return new ResponseEntity("Driver saved!", HttpStatus.OK);
        }
        return new ResponseEntity("Error, failed to save driver", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Member findMemberById(int id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public ResponseEntity<?> deleteMember(int id) {
        Member memberExist = memberRepository.findById(id);
        if(memberExist==null) {
            return new ResponseEntity("Failed to delete Member!", HttpStatus.BAD_REQUEST);
        }
        memberRepository.delete(memberExist);
        return new ResponseEntity("Successed to delete Member", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateMemberBalance(int id, double balance,boolean bool) {
        Member member = findMemberById(id);
        if(member==null) return new ResponseEntity("Member not found!",HttpStatus.NOT_FOUND);
        if(bool){
            member.setBalance(member.getBalance()-balance);
        }else{
            member.setBalance(member.getBalance()+balance);
        }
        memberRepository.save(member);
        return new ResponseEntity("Balance Updated",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateMemberProfile(int id, Member newMember) {
        Member member = memberRepository.findById(id);
        if(member == null)return new ResponseEntity("Member not found!", HttpStatus.BAD_REQUEST);
        member.setBalance(newMember.getBalance());
        member.setName(newMember.getName());
        memberRepository.save(member);
        return new ResponseEntity("Successed to update Member", HttpStatus.OK);
    }

}
