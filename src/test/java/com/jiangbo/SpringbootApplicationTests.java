package com.jiangbo;

import com.jiangbo.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private TestService testService;

	@Transactional(rollbackFor = {Exception.class})
	@Test
	public void contextLoads() {
		com.jiangbo.entity.Test testById = testService.getTestById(1);

		System.out.println(testById);
		System.out.println("================");

		com.jiangbo.entity.Test test = new com.jiangbo.entity.Test();
		test.setAge(28);
		test.setName("王婷");

		testService.addTest(test);

		System.out.println(1 / 0);
	}

}
