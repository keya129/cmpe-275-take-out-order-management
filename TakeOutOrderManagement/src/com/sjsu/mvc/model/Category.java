package com.sjsu.mvc.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@Entity
@MappedSuperclass
public abstract class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_NUM")
	@SequenceGenerator(name="SEQ_NUM",sequenceName="SEQ_NUM",allocationSize=1,initialValue=0)
	
	protected int menuId;
	private String categoryType;
	
	
	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public int getMenuId(){
		return menuId;
	}
	
	public void setMenuId(int menuId){
		this.menuId=menuId;
	}
	
}