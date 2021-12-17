package com.icia.ajb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.ajb.dto.CommentDTO;
import com.icia.ajb.repository.CommentRepository;


@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentRepository cr;

	@Override
	public void save(CommentDTO comment) {
		cr.save(comment);
		
	}

	@Override
	public List<CommentDTO> findAll(long b_number) {
		
		return cr.findAll(b_number);
	}
}
