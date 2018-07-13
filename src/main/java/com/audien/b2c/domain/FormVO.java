package com.audien.b2c.domain;

//http://springmvc.egloos.com/509029

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class FormVO {
	@NotEmpty
	private String name;
	
	@Range(min = 1, max = 150) //age need between 1 and 150
	int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FormVO [name=" + name + ", age=" + age + "]";
	}


	
}
