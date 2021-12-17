package com.icia.ajb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icia.ajb.dto.BoardDTO;
import com.icia.ajb.dto.CommentDTO;
import com.icia.ajb.dto.PageDTO;
import com.icia.ajb.service.BoardService;
import com.icia.ajb.service.CommentService;


// /board/* 주소를 모두 처리
@Controller
@RequestMapping(value="/board/*") // /board/* 로 요첳하는 주소는 다 받겠다
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private CommentService cs;
	
	// 글쓰기 페이지 출력
	@RequestMapping(value="save", method=RequestMethod.GET)
	public String saveForm() {
		// views/board 폴더에 있는 save.jsp를 출력
		return "/board/save";
	}
	
	// 보낸걸 받아야지 글쓰기 저장
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@ModelAttribute BoardDTO board) {
		bs.save(board);
		return "redirect:/board/paging";
	}
	// 파일 저장 글 보내기
	@RequestMapping(value="savefile", method=RequestMethod.POST)
	public String savefile(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException{
		bs.saveFile(board);
		return "redirect:/board/paging";
	}
	// 글쓴기 목록 처리
	@RequestMapping(value="findAll", method=RequestMethod.GET)
	public String findAll(Model model) {
		List<BoardDTO>boardList = bs.findAll();
		model.addAttribute("boardList",boardList);
		return "board/findAll";
	}
	
	// 상세조회
	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String findById(@RequestParam("b_number") long b_number, Model model,@RequestParam(value="page", required=false, defaultValue="1")int page) {
		BoardDTO board = bs.findById(b_number);
		model.addAttribute("board",board);
		model.addAttribute("page",page);// detail.jsp로 갈 때 page 값을 가지고 감.
		List<CommentDTO>commentList = cs.findAll(b_number);
		model.addAttribute("commentList",commentList);
		return "board/detail";
	}
	
	// 삭제 처리
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(@RequestParam("b_number")long b_number) {
		bs.delete(b_number);
		return "redirect:/board/findAll";
	}
	
	// 수정 화면요청
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(@RequestParam("b_number")long b_number,Model model,@RequestParam(value="page", required=false, defaultValue="1")int page) {
		BoardDTO board = bs.findById(b_number);
		model.addAttribute("board",board);
		model.addAttribute("page",page);
		return "board/update";
	}
	
	// 수정처리
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardDTO board,@RequestParam(value="page", required=false, defaultValue="1")int page) {
		bs.update(board);
		return "redirect:/board/detail?b_number="+ board.getB_number() + "&page=" +page;
	}
	
	// 페이징 
	@RequestMapping(value="paging", method=RequestMethod.GET)
	// value: 파라미터리음, required: 필수여부 , defaultValue: 기본값(page 요청값이 없으면 1로 세팅)
	public String paging(@RequestParam(value="page", required=false, defaultValue="1")int page, Model model) {
		List<BoardDTO> boardList = bs.pagingList(page);
		PageDTO paging = bs.paging(page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		return "board/findAll";
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@RequestParam("searchtype") String searchtype, @RequestParam("keyword") String keyword, Model model) {
		List<BoardDTO>boardList = bs.search(searchtype,keyword );
		model.addAttribute("boardList",boardList);
		return "board/findAll";
	}
	
	
}
