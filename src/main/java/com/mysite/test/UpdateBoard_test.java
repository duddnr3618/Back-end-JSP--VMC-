package com.mysite.test;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class UpdateBoard_test {

	public static void main(String[] args) {

		//1. DTO에 값을 할당.
		BoardDTO dto = new BoardDTO ();
		dto.setTitle("제목 - 3 수정 (test)");
		dto.setContent("내용 - 3 수정 (test)");
		dto.setSeq(3);
		
		//2.DAO에서 updateBoard(dto) 
		BoardDAO dao = new BoardDAO ();
		dao.updateBoard(dto);
		
		
		
	}

}
