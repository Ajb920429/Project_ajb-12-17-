package com.icia.ajb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.ajb.dto.MemberDTO;
import com.icia.ajb.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository mr;
	
	@Autowired
	private HttpSession session;
	
	
	@Override
	public void save(MemberDTO member) throws IllegalStateException, IOException{
			 
				MultipartFile m_file = member.getM_file();
			
				String m_filename = m_file.getOriginalFilename();
			
				m_filename = System.currentTimeMillis() + "-" + m_filename;
				
				String savePath = "C:\\development\\source\\spring\\Project\\src\\main\\webapp\\resources\\upload\\"+m_filename;
				
				if(!m_file.isEmpty()) {
					m_file.transferTo(new File(savePath));
				}
				
				member.setM_filename(m_filename);
				
				
				mr.save(member);
	}

	@Override
	public String login(MemberDTO member) {
		System.out.println("s"+member);
		// login 메서드 리턴타입을 MemberDTO로 했으니 여기서도 좌변에 MemberDTO로 받아야지
		// 이건 수업시간에 했었던거니 참고해봐요 다시실행
		MemberDTO loginMember = mr.login(member);
		if(loginMember != null){
			session.setAttribute("loginId", member.getM_id());
			session.setAttribute("loginNember", member.getM_number());
			return "redirect:/board/paging";
		}else {
			return "/member/login";
		}
	}
	
	


	@Override
	public List<MemberDTO> findAll() {
		List<MemberDTO>memberList = mr.findAll();
		return memberList;
	}


	@Override
	public MemberDTO findById(long m_number) {
		MemberDTO member = mr.findById(m_number);
		return member;
	}


	@Override
	public void delete(long m_number) {
		mr.delete(m_number);
		
	}


	@Override
	public void update(MemberDTO member) throws IllegalStateException, IOException {
		MultipartFile m_file = member.getM_file();
		String m_filename = m_file.getOriginalFilename();
		m_filename = System.currentTimeMillis() + "-" + m_filename;
		String savePath = "C:\\development\\source\\spring\\Project\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		if(!m_file.isEmpty()) {
			m_file.transferTo(new File(savePath));
		}
		
		member.setM_filename(m_filename);
		
		mr.update(member);
		
	}

	// id 중복체크
	@Override
	public String idDuplicate(String m_id) {
		String result = mr.idDuplicate(m_id);
		if(result == null)
			return "ok"; // 조회결과가 없기 때문에 해당 아이디는 사용가능
		else
			return "no"; // 조회결과가 있기 때문에 해당 아이디는 사용불가능
	}

	@Override
	public MemberDTO mypage(long m_number) throws IllegalStateException, IOException {
		MemberDTO member = mr.mypage(m_number);
		// dto에 담긴 파일을 가져옴 
		MultipartFile m_file = member.getM_file();
		session.setAttribute("loginId", member.getM_id());
		session.setAttribute("loginNember", member.getM_number());
		return member;
	}


	
	
	









	

}
