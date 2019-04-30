package domain;

import java.io.Serializable;

public class Item implements Serializable {
	//property를 만들 때 되도록이면 테이블의 컬럼이름과 일치시키는 것이 좋다.
	private int code;
	private String title;
	private String category;
	private String description;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Item [code=" + code + ", title=" + title + ", category=" + category + ", description=" + description
				+ "]";
	}
	
}
