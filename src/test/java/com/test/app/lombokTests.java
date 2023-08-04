package com.test.app;

import org.junit.Test;

import com.test.app.domain.dto.MemoDto;
import com.test.app.domain.dto.Person;

public class lombokTests {
	
	
	@Test
	public void test() {
		Person obj = new Person();
		obj.setName("홍길동");
		
		obj.setAddr("달서구");
		
		obj.setAge(12);
		System.out.println(obj);
	}
	
	@Test
	public void test2() {
		MemoDto dto = MemoDto.builder()
						.writer("홍길동")
						.id(2)
						.text("내용")
						.build();
		System.out.println("DTO " + dto);
	}
	
}
