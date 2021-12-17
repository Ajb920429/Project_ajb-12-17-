package com.icia.ajb.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	
	private long b_number;
	private String b_writer;
	private String b_password;
	private String b_title;
	private String b_contentsl;
	private int b_hits;
	private Timestamp b_date;
	private MultipartFile b_file; // jsp에서 컨트롤러로 넘어올 때 파일 자체를 담는 필드
	private String b_filename; // DB에 파일을 담는 것이 아니라 파일 이름을 담는 것. 파일은 별도의 경로에 저장
	

}
