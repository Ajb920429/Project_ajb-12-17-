package com.icia.ajb.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.ajb.dto.BoardDTO;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSessionTemplate sql; 
	


	public void save(BoardDTO board) {
		sql.insert("Board.save",board);
		
	}



	public List<BoardDTO> findAll() {
		
		return sql.selectList("Board.findAll");
	}



	public BoardDTO findById(long b_number) {
		
		return sql.selectOne("Board.findById",b_number);
	}


	public void hits(long b_number) {
		sql.update("Board.hits",b_number);
		
	}

	public void delete(long b_number) {
		sql.delete("Board.delete",b_number);
		
	}


	public void update(BoardDTO board) {
		sql.update("Board.update",board);
		
	}
	
	// 페이징
	public int boardCount() {
		return sql.selectOne("Board.count");
	}
	
	public List<BoardDTO> pagingList(int pagingStart) {
		return sql.selectList("Board.pagingList", pagingStart);
	}

	public List<BoardDTO> pagingList1(Map<String, Integer> pagingParam) {
		return sql.selectList("Board.pagingList1", pagingParam);
	}



	public List<BoardDTO> search(Map<String, String> searchParam) {
		
		return sql.selectList("Board.search", searchParam);
	}



	public void saveFile(BoardDTO board) {
		sql.insert("Board.saveFile",board);
	}



	
	
	
	

}
