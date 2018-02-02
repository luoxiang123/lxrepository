package com.qf.test;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.service.RankService;
import com.qf.service.impl.RankServiceImpl;

public class Quartz_Test {

	public static void main(String[] args) throws Exception {
		cron();
	}

	public static void test02() throws Exception {
		// ��ȡ�ƻ�����
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// ��������---���ڿ�ʼ��û���ظ�һ��
		Trigger trigger = TriggerBuilder.newTrigger().startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
		// ��ҵ����
		JobDetail jobDetail = JobBuilder.newJob(Hello.class).build();
		// ��������������ҵ��ӵ��ƻ���
		scheduler.scheduleJob(jobDetail, trigger);
		// �����ƻ�
		scheduler.start();
	}

	@Test
	public static void test01() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("����");
			}
		}, 0, 2000);
	}

	public static void cron() throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		Job job = new Job() {
			@Override
			public void execute(JobExecutionContext context) throws JobExecutionException {
				// TODO Auto-generated method stub
				System.out.println("�ܷ���!");
			}
		};
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cron", "group")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * 16-17 * * ?")).build();
		scheduler.scheduleJob(JobBuilder.newJob(Hello.class).build(), cronTrigger);
		scheduler.start();
	}

	public static void test03() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
		RankService service = context.getBean(RankService.class);
		JobDetail jobDetail = JobBuilder.newJob(RankJob.class).build();
	}
}
