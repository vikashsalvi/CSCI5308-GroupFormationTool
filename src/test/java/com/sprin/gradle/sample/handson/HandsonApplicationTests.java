package com.sprin.gradle.sample.handson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HandsonApplicationTests {

	@Test
	void contextLoads() {
		String obj1 = "Junit";
		String obj2 = "Junit";
		System.out.println("In test");
		assertEquals(obj1, obj2);
	}

}


