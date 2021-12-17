package com.icia.ajb.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.ajb.dto.CommentDTO;



@Repository
public class CommentRepository {

	@Autowired
	private SqlSessionTemplate sql;
		
	public void save(CommentDTO comment) {
		sql.insert("Comment.save",comment);
		
	}

	public List<CommentDTO> findAll(long b_number) {
		// TODO Auto-generated method stub
		return sql.selectList("Comment.findAll",b_number);
	}

}
