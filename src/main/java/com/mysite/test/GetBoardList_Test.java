package com.mysite.test;

import java.util.ArrayList;
import java.util.List;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class GetBoardList_Test {

	public static void main(String[] args) {
		// 1.DTO객체 생성
		BoardDTO dto = new BoardDTO ();	//패키지가 다르기때문에 임포트
		
		// 2.DAO객체 생성
		BoardDAO dao = new BoardDAO ();
		
		// 3.DAO 메소드 호출시 리턴 받을 리스트 변수선언 : ArrayList 선언
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		//boardList는 board테이블의 레코드를 dto에 저장후 List에 추가됨.
		boardList = dao.getBoardList(dto);
		
		//ArrayList에 저장된 값을 끄집어내서 출력
		//for문을 사용해서 출력
		System.out.println("===for문을 사용해서 출력 ======");
		for (int i = 0 ; i < boardList.size(); i++) {
			System.out.println(boardList.get(i)); 	
		}

		//Enhance for문을 사용해서 출력
		System.out.println("===Enhanced for문을 사용해서 출력 ======");
		for (BoardDTO k : boardList) {
			System.out.println(k);
		}
	}

}
