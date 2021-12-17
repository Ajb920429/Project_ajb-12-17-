package com.icia.ajb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icia.ajb.dto.BoardDTO;
import com.icia.ajb.dto.PageDTO;

@Service
public interface BoardService {
	
	// interface
	// 추상메서드를 가짐. => 실행블록은 없고 메서드 선언부만 있는 것
	// interface를 구현(implements)하는 구현 클래스에서 실행블록을 작성함.
	
	
	

	public void save(BoardDTO board);

	public List<BoardDTO> findAll();

	public BoardDTO findById(long b_number);

	public void delete(long b_number);

	public void update(BoardDTO board);

	public List<BoardDTO> pagingList(int page);

	public PageDTO paging(int page);

	public List<BoardDTO> search(String searchtype, String keyword);

	public void saveFile(BoardDTO board) throws IllegalStateException, IOException;



	
	

}
