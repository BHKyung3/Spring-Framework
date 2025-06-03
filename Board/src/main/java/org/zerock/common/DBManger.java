package org.zerock.common; // 패키지 명

// import : 다른 패키지에 있는 클래스나 인터페이스를 내 코드에 가지고와 사용하겠다는 의미
// sql : DB에게 자료를 달라고 하거나, 자료를 저장하거나, 수정하거나 삭제하는 명령어
import java.sql.Connection; // 데이터베이스와 연결하는 객체, DB 연결이 성공하면 Connection객체가 만들어지고 이를 통해 sql을 보낼 수 있음
import java.sql.ResultSet; // 결과를 표 형태로 받아오는 객체
import java.sql.SQLException; // JDBC 작업 중 발생할 수 있는 DB 오류를 잡아주는 예외 클래스
import java.sql.Statement; // SQL 구문(SELECT, INSERT, UPDATE, DELETE)을 DB에 보내는 객체

public class DBManger { // 외부에서 접근 가능하며 class명은 DBManger
	
	// 외부에서(public) 객체없이(static) 부를 수 있는 값은 돌려주지 않는(void) 메소드(close)를 만든다. 
	// 이 객체는 DB 연결(변수이름 conn), 명령어 실행(변수이름 stmt), 결과값(변수이름 rs)을 받아서 닫는다
	public static void close(Connection conn, Statement stmt,
			ResultSet rs) {
		try { // 오류가 발생할 수 있는 부분을 시도하는 블록
			if(rs !=null)  rs.close(); // 만약에 rs가 null이 아니라면(존재한다면) close() 메소드로 닫는다 // 데이터베이스 결과를 사용한 후 리소스를 반환하고 메모리 누수를 방지하고자 사용
			stmt.close();
			conn.close();
		} catch (SQLException e) { // if 문에서 잘못 된 내용이 있다면 에러 났다고 잡는 구문
			e.printStackTrace(); // 에러가 났을 때 여기서 문제가 생겼다고 보여주는 부분
		}
	} // try 끝(조회할 때 사용)
	// 상황에 따라 ResultSet이 없는 경우도 있기 때문에 기재
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // try 끝(추가, 수정, 삭제 등에 사용)
	
}