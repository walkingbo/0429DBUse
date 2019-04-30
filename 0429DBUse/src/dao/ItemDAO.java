package dao;

import java.util.List;

import domain.Item;

//repository - 저장소
//저장소에 접근하는 인스턴스 : Data Access Object
public interface ItemDAO {
	//item 테이블의 전체 데이터를 가져오는 메소드
	//리턴 타입은 배열이나 List
	//Set도 가능하지만 Set은 순서를 알 수 없어서 출력할 때 정렬을 다시 해야한다.
	//배열도 개수를 미리 알아야 해서 번거롭기 때문에 잘 사용안함
	public List<Item> allItems();
}
