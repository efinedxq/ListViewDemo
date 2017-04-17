package com.example.work_5_1;

import java.util.List;

public class User {
    private String name;
    private String sex;
    private String age;
    private List<String> fav;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public List<String> getFav() {
		return fav;
	}
	public void setFav(List<String> fav) {
		this.fav = fav;
	}
    
}
