package com.app.group15;

import com.app.group15.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Group15ApplicationTests {

	@Test
	void contextLoads() {
		AppConfig.instance();

		assertEquals(true,true);
	}

}
