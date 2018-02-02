package com.qf.controller;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.pojo.JsonResult;
import com.qf.pojo.Rank;
import com.qf.service.QuartzRank;
import com.qf.service.RankService;
import com.qf.test.RankJob;

@Controller
public class RankController {
	@Autowired
	private RankService rankService;
	@Autowired
	private QuartzRank quartzRank;

	@RequestMapping("index.do")
	public String QuartzRank() throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("rank1", "grouprank1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 51-52 * * * ?")).build();
		JobDetail jobDetail = JobBuilder.newJob(RankJob.class).build();
		jobDetail.getJobDataMap().put("quartzrank", quartzRank);
		scheduler.scheduleJob(jobDetail, cronTrigger);
		scheduler.start();
		return "rank";
	}

	@RequestMapping(value = "add.do", method = { RequestMethod.POST }, params = { "score" })
	@ResponseBody
	public JsonResult add(Rank rank) {
		if (rankService.save(rank)) {
			return JsonResult.setOK("");
		} else {
			return JsonResult.setError("");
		}
	}

	@RequestMapping(value = "rankpage.do", method = { RequestMethod.GET }, params = { "page", "size" })
	@ResponseBody
	public JsonResult page(int page, int size) {
		Page<Rank> pages = rankService.getByPage(page, size);
		return JsonResult.setOK(pages);
	}

	@RequestMapping(value = "ranklike.do", method = { RequestMethod.GET }, params = { "projectName" })
	@ResponseBody
	public JsonResult ranklike(String projectName) {
		List<Rank> list = rankService.findByProjectNameLike("%" + projectName + "%");
		System.out.println(list);
		return JsonResult.setOK("");
	}
}
