package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import domain.Item;
import util.GlobalApplication;

public class ItemDAOImpl implements ItemDAO {
	
	//데이터 베이스 연결 객체 변수 - 데이터베이스 프레임 워크 변수로 변경
		private Connection con;
	
	//싱글톤 패턴
	private ItemDAOImpl() {
	//인스턴스 자신의 인스턴스 변수의 데이터를 직접 생성하지 않고
	//외부에서 생성한 데이터를 대입하는 것을 DI(Dependency Injection)
	//라고 합니다.
	//만드는 것에 신경쓰지 않고 사용하는 부분에만 집중
	//비지니스 로직만 생성하면 됨	
	con = GlobalApplication.shardInstance().con;

	}
	//클라이언트나 다른 개발자들은 구현될 코드를 볼 필요가 없기 때문에
	//메소드 모양만 보면 되므로 변수는 인터페이스 타입으로 선언
	private static ItemDAO itemDAO;
	
	public static ItemDAO getInstance() {
		if(itemDAO == null) {
			itemDAO = new ItemDAOImpl();
		}
		return itemDAO;
	}
	
	
	@Override
	public List<Item> allItems() {
		List<Item> list = new ArrayList<Item>();
		//SQL 실행 객체를 저장할 변수
		PreparedStatement pstmt = null;
		//Select 구문의 결과를 저장할 변수
		ResultSet rs = null;
		try {
			//item 테이블의 전체 데이터를 가져오는 SQL 실행
			pstmt =con.prepareStatement(
					"select code, title, category, description from item");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setCode(rs.getInt("code"));
				item.setTitle(rs.getString("title"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
				list.add(item);
			}
		}catch(Exception e) {
			//예외 처리를 할 때는 메시지 출력
			//학습을 위해서 라면 예외를 파일에 기록
			System.out.println("DAO 전체가져오기:"+e.getMessage());
			//예외를 역 추적
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return list;
	}
}
