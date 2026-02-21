package com.example.library.dao;

import com.example.library.model.Member;

import java.util.List;

public interface MemberDAO {
    void save(Member member);
    Member findById(int id);
    List<Member> findAll();
    void update(Member member);
    void delete(int id);
}
