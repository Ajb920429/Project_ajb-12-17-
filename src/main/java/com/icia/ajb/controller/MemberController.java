package com.icia.ajb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.ajb.dto.MemberDTO;
import com.icia.ajb.service.MemberService;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	private MemberService ms;
	
	// 회원가입 페이지 출력
	@RequestMapping(value="save", method=RequestMethod.GET)
	public String saveForm() {
		return "/member/save";
	}
	
	// 보낸걸 받아야지 회원가입 처리
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		System.out.println("member_controller.save():" +member);
		ms.save(member);
		return "index";
	
	}
	
	// 로그인 페이지 출력
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginForm() {
		return "/member/login";
	}
	
	// 로그인 처리
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member) {
		System.out.println("c"+member);
		String resultPage	= ms.login(member);
		return resultPage;
	}
	
	// 로그아웃 처리
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		// 세션제 저당된 데이터를 지움.
		session.invalidate();
		return "index";
	}
	
	// 회원목록 처리
	@RequestMapping(value="findAll",method=RequestMethod.GET)
	public String findAll(Model model) {
		List<MemberDTO>memberList = ms.findAll();
		model.addAttribute("memberList",memberList);
		return "/member/findAll";
	}
	
	// 상세조회 처리
	
	@RequestMapping(value="detail",method=RequestMethod.GET)
	public String findById(@RequestParam("m_number") long m_number, Model model) {
		MemberDTO member = ms.findById(m_number);
		model.addAttribute("member",member);
		return "/member/detail";
	}
	
	// 삭제 처리
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(@RequestParam("m_number")long m_number) {
		
		ms.delete(m_number);
		return "redirect:/member/findAll";
	}
	

	
	// 수정화면요청
	

	// 수정처리
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute MemberDTO member, Model model) throws IllegalStateException, IOException{
		ms.update(member);
		// DB에서 데이터를 가지고 와서 detail.jsp로
		// member = ms.findById(member.GetM_number);
		// model.addAttribute("member",member);
		// return "detail";
		return "redirect:/member/detail?m_number="+ member.getM_number();
	}
	
	// 아이디 중복체크
	@RequestMapping(value="idDuplicate", method=RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam("m_id") String m_id ) {
		System.out.println("idDuplicate: " + m_id);
		String result = ms.idDuplicate(m_id);
		return result; // "ok" or "no"
	}
	
	// ajax로 상세 조회
	@RequestMapping(value="detailAjax", method=RequestMethod.POST)
	public @ResponseBody MemberDTO detailAjax(@RequestParam("m_number") long m_number) {
		MemberDTO member = ms.findById(m_number);
		return member;
	}
	
	// 마이 페이지 
		@RequestMapping(value="mypage", method=RequestMethod.GET)
		public String mypage(Model model, @RequestParam("m_number") long m_number) throws IllegalStateException, IOException {
			MemberDTO m = ms.mypage(m_number);
			model.addAttribute("mypage",m);
			return "/member/mypage";
		}
		// 수정 화면 요청
		@RequestMapping(value="update", method=RequestMethod.GET)
		public String updatef(Model model, @RequestParam("m_number") long m_number) throws IllegalStateException, IOException {
			MemberDTO m = ms.mypage(m_number);
			model.addAttribute("update", m);
			return "/member/update";
		}


		
}
