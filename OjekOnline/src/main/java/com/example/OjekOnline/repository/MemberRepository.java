package com.example.OjekOnline.repository;

import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findById(int id);
}
