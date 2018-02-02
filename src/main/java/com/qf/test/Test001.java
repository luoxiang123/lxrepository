package com.qf.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qf.pojo.Rank;
import com.qf.service.RankService;

public class Test001 {
	@Test
	public void test001() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring-dao.xml");
		RankService rankService = (RankService) applicationContext.getBean("rankServiceImpl");
		Rank rank = new Rank();
		rank.setProjectName("´î»ï");
		rank.setGroupNo(8);
		rank.setScore(100);
		// rankService.save(rank);
		System.out.println(rankService.getByPage(2, 10).getContent());
	}
}
