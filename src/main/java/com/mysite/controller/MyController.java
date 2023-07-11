package com.mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;
import com.mysite.users.UsersDAO;
import com.mysite.users.UsersDTO;

/**
 * Servlet implementation class MyController
 */
//@WebServlet("/MyController")      <== 어노테이션으로 요청을 처리 ( spring ) 
	/*
	    Controller : Client 의 요청을 처리함. ( get, post )
	     		- doGet() {} : client 에서 보내는 get 요청을 처리하는 메소드 ,   <form method = "get" action= "a.jsp">, <a href = "b.jsp"> 
	     		- doPost() {} : client 에서 보내는 Post 요청을 처리하는 메소드 , <form method = "post" action="a.jsp"> 
	     		
	      controller 요청을 처리 하는 방법 : 
	      		web.xml : 클라이언트의 요청에 대한 controller를 지정함. 
	      		@WebServlet : 어노테이션을 사용해서 처리 
	      		
	      		
	      client 가 보내는 요청정보의 URI를 캐취해서 분기처리 
	 */

public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at (MyController) : ").append(request.getContextPath());
		
		// client 에서 get 방식으로 넘어오는 요청 처리를 doPost에서 한꺼번에 처리하도록 던져줌 		
		doPost ( request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//client 에서 Get / Post 모두를 한꺼번에 처리함. 
		
		// URL : http://localhost:8181/JSP_Study_MVC_M2/my.do
		// URI : /JSP_Study_MVC_M2/my.do
		
		request.setCharacterEncoding("UTF-8");	//한글이 깨지는거 방지
		
		//1. Client의 요청 정보를 path 변수에 등록 함. 
		String url = request.getRealPath(getServletInfo()); 
			//System.out.println("클라이언트가 보내는 전체 URL(실제 시스템의 물리적 경로)  : " + url );
		
		StringBuffer urll = request.getRequestURL();
		System.out.println("system URL : " + urll);
		
		String uri = request.getRequestURI(); 
			//System.out.println("클라이언트가 보내는 요청 uri : " + uri);
		
		String path = uri.substring(uri.lastIndexOf("/")); 
			//System.out.println("파일 명을 읽어옴 : " + path );
			
		//2. 클라이언트의 요청 정보에 따라서 적절히 분기 처리 한다. 
		if ( path.equals("/login.do")) {       
			System.out.println("login.do 요청을 했습니다. ");
			// 로그인을 처리하는 코드 블락 
			
		} else if (path.equals("/logout.do")) {
			System.out.println("logout.do 요청을 했습니다. ");
			//로그아웃 요청을 처리하는 코드 블락 
			
		} else if (path.equals("/insertBoard.do")) {
			System.out.println("insertBoard.do 요청을 했습니다. ");
			// 게시판에서 값을 DB에 저장함.
			
			// 1. client 폼에서 넘어오는 변수의 값을 받아서 새로운 변수에 제 할당. 
			String title = request.getParameter("title"); 
			String writer = request.getParameter("writer"); 
			String content = request.getParameter("content");
			
			//2. DTO 객체를 생성해서 Setter 주입 
			BoardDTO dto = new BoardDTO (); 
			dto.setTitle(title); 
			dto.setWrite(writer); 
			dto.setContent(content); 
			
			//3. DAO 객체 생성후 insertBoard(dto)  
			BoardDAO dao = new BoardDAO (); 
			dao.insertBoard(dto);        //Insert 완료 
			
			//4. 비즈니스 로직을 처리후 view 페이지로 이동 
			response.sendRedirect("getBoardList.do"); 
			
			
		} else if (path.equals("/getBoard.do")) {
			System.out.println("getBoard.do 를 요청 했습니다. ");
			// 게시판의 값을 읽어 올때 
			
		}else if (path.equals("/getBoardList.do")) {
			System.out.println("getBoardList.do 를 요청 했습니다. ");
			
			//1.DTO객체 생성
			BoardDTO dto = new BoardDTO ();
			
			//2.DAO의 getBoardList(dto)
			BoardDAO dao = new BoardDAO ();
			List<BoardDTO> boardList = new ArrayList<BoardDTO> ();
			
			//boarList에는 board테이블의 각 레코드를 dto에 저장후 boardList에 추가된 객체를 리턴
			boardList = dao.getBoardList(dto);
			
			//리턴받은 boardList를 클라이언트 뷰 페이지로 전송 -> Session에 리스트를 저장후 클라언트로 전송
			//Session 변수선언 : 접속한 클라이언트에 고유하게 부여된 식별자가 서버 메모리에 할당
			HttpSession session = request.getSession();
			
			//Session에 boardList를 추가
			session.setAttribute("boardList", boardList);
			
			//클라이언트 뷰 페이지로 이동
			response.sendRedirect("getBoardList.jsp");
			
		}else if (path.equals("/insertUsers.do")) {
			System.out.println("/insertUsers.do - 요청 ");
			//Users테이블에 값을 Insert 코드 블락 
			
			//1. Form에서 넘어오는 값을 읽어와서 새로운 변수에 할당 
			String id = request.getParameter("id"); 
			String password = request.getParameter("password"); 
			String name = request.getParameter("name"); 
			String role = request.getParameter("role"); 
			
			//2. DTO 에 값을 할당. 
			UsersDTO dto = new UsersDTO(); 
			dto.setId(id); 
			dto.setPassword(password); 
			dto.setName(name); 
			dto.setRole(role); 
			
			//3. DAO 의 메소드 호출  : insertUsers(dto) 
			UsersDAO dao = new UsersDAO(); 
			dao.insertUsers(dto); 
			
			//4. 비즈니스로직 모두 처리후 view 페이지 이동 
			response.sendRedirect("getUsersList.do"); 
			
		}else if ( path.equals("/getUsersList.do")) {
			System.out.println("/getUsersList.do 요청 ");
			//Users테이블의 값을 Select 해서 출력 
			
			//1. 객체 생성 
			UsersDTO dto = new UsersDTO(); 
			UsersDAO dao = new UsersDAO(); 
			
			//2. 메소드 호출후 리턴으로 List 받음. 
			List<UsersDTO> usersList = new ArrayList<UsersDTO>(); 
			usersList = dao.getUsersList(dto); 
			
			//3. Session 객체를 사용해서 변수에 userList 를 저장함. 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("usersList", usersList); 
			
			
			//4. 비즈니스로직 모두 처리후 view 페이지 이동 
			response.sendRedirect("getUsersList.jsp"); 
		}
			
		
		
	}

}