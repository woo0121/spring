package com.test.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.app.domain.dto.Person;

@Configuration
public class AppConfiguration {
	
	@Bean
	public Person MyBean() { // 이 메서드 이름을 MyBean으로 하거나, 다른 원하는 이름으로 변경할 수 있습니다.
		return new Person().builder()
				.name("가나다")
				.age(45)
				.addr("대구")
				.build(); // 이 부분을 build()로 수정합니다.
	}
}