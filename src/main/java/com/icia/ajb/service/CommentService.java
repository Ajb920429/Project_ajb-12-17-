package com.icia.ajb.service;

import java.util.List;

import com.icia.ajb.dto.CommentDTO;

public interface CommentService {

	void save(CommentDTO comment);

	List<CommentDTO> findAll(long b_number);

}
