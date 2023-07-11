package com.mysite.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysite.common.JDBCUtil;

public class UsersDAO {
	
	private Connection conn = null ; 
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null; 
	
	//SQL 쿼리를 상수로 정의후에 각각 필요한 메소드에서 사용  
	private final String USERS_INSERT = 
			"insert into users (id, password, name, role) values (?,?,?,?)"; 
	private final String USERS_UPDATE = ""; 
	private final String USERS_DELETE = ""; 
	private final String USERS_GET = ""; 
	private final String USERS_LIST = "select * from users order by id asc"; 
	
	
	//1. users 테이블의 값을 넣는 메소드 
	public void insertUsers(UsersDTO dto) {
		System.out.println("=====insertUsers () 메소드 호출");
	
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(USERS_INSERT); 
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getRole());
			
			pstmt.executeLargeUpdate(); 
			
			System.out.println("insertUsers () - 성공 ");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertUsers () - 실패 ");
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	//2. users 테이블의 모든 레코드를 출력 메소드 
	public List<UsersDTO> getUsersList(UsersDTO dto){
		System.out.println("=====getUsersList () 메소드 호출");
		List<UsersDTO> userList = new ArrayList<UsersDTO>(); 
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(USERS_LIST); 
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				UsersDTO users = new UsersDTO();
				users.setId(rs.getString("ID"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setName(rs.getString("NAME"));
				users.setRole(rs.getString("ROLE"));
				
				userList.add(users); 
			
			}
			
			System.out.println("=====getUsersList () 메소드 -  성공");

			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("=====getUsersList () 메소드 -  실패");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return userList; 
			
	}
}