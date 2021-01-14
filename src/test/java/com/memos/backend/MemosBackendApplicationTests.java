package com.memos.backend;

import com.memos.backend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemosBackendApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void myTestTuni() {
		if (userRepository.findByEmail("asd@m.com") == null)
			System.out.println("OK");
	}
}
