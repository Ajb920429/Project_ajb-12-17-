package com.icia.ajb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icia.ajb.dto.MemberDTO;

@Service
public interface MemberService {

	public void save(MemberDTO member) throws IllegalStateException, IOException;

	public String login(MemberDTO member);

	public List<MemberDTO> findAll();

	public MemberDTO findById(long m_number);

	public void delete(long m_number);

	public void update(MemberDTO member) throws IllegalStateException, IOException;

	public String idDuplicate(String m_id);

	public MemberDTO mypage(long m_number) throws IllegalStateException, IOException;

	





	



	



	



	

}
