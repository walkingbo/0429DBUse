package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import domain.Item;

public class ItemServiceImpl implements ItemService {
	//DAO 객체를 저장하기 위한 변수
	private ItemDAO itemDAO;
	
	//싱글톤 패턴
		private ItemServiceImpl() {
		//인스턴스 자신의 인스턴스 변수의 데이터를 직접 생성하지 않고
		//외부에서 생성한 데이터를 대입하는 것을 DI(Dependency Injection)
		//라고 합니다.
		//만드는 것에 신경쓰지 않고 사용하는 부분에만 집중
		//비지니스 로직만 생성하면 됨	
		itemDAO = ItemDAOImpl.getInstance();

		}
		//클라이언트나 다른 개발자들은 구현될 코드를 볼 필요가 없기 때문에
		//메소드 모양만 보면 되므로 변수는 인터페이스 타입으로 선언
		private static ItemService itemService;
		
		public static ItemService getInstance() {
			if(itemService == null) {
				itemService = new ItemServiceImpl();
			}
			return itemService;
		}
	@Override
	public List<Item> allItems(HttpServletRequest request) {
		List<Item> list = new ArrayList<Item>();
		
		//파라미터 읽기
		
		//파라미터를 DAO 메소드의 매개변수에 맞게 만들기
		
		//DAO의 메소드를 호출해서 결과를 저장
		list = itemDAO.allItems();
		
		
		//결과를 리턴
		return list;
	}

}
