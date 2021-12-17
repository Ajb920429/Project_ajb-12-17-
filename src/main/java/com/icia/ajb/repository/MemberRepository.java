package com.icia.ajb.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.ajb.dto.MemberDTO;

@Repository
public class MemberRepository {
	@Autowired
	private SqlSessionTemplate sql;
	
	
	public int save(MemberDTO member) {
		System.out.println("MemberRepository.save(): "+member);
		return sql.insert("Member.save",member);
	}

	// 메서드 리턴타입도 MemberDTO로 
	public MemberDTO login(MemberDTO member) {
		// resultType이 member니까 좌변을 DTO 타입으로 받아야하고 
		MemberDTO loginMember  = sql.selectOne("Member.login",member);
		// 리턴을 loginMember를 주고  
		return loginMember;
		// 실행해봐요
		
	}


	public List<MemberDTO> findAll() {
		
		return sql.selectList("Member.findAll");
	}


	public MemberDTO findById(long m_number) {
		// TODO Auto-generated method stub
		return sql.selectOne("Member.findById",m_number);
	}


	public void delete(long m_number) {
		sql.delete("Member.delete",m_number);
	}


	public void update(MemberDTO member) {
		sql.update("Member.update",member);
		
	}


	public String idDuplicate(String m_id) {
		
		return sql.selectOne("Member.idDuplicate",m_id);
	}

	public MemberDTO mypage(long m_number) {
		// TODO Auto-generated method stub
		return sql.selectOne("Member.mypage", m_number);
	}

	

	

	

	


	

}
