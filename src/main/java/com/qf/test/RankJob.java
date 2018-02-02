package com.qf.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.qf.service.QuartzRank;

public class RankJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		QuartzRank rank = (QuartzRank) context.getJobDetail().getJobDataMap().get("quartzrank");
		System.out.println(rank);
		System.out.println("定时备份:"+System.currentTimeMillis()/1000);
		rank.barkRank();
	}
}
