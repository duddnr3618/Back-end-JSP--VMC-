package com.mysite.test;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class Getboard_test {

	public static void main(String[] args) {
		
		// 1. dto에 seq변수에 값을 할당
		BoardDTO dto = new BoardDTO ();
		dto.setSeq(3);
		
		// 2.dao의 메소드 호출
		BoardDAO dao = new BoardDAO ();

		BoardDTO board = new BoardDTO ();
		board = dao.getBoard(dto);		//board : DB에서 읽어온 값을 저장
		
		// 3.출력
		System.out.println(board);
		
		
		
	}

}
