package com.qf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qf.pojo.Rank;
import com.qf.service.QuartzRank;
import com.qf.service.RankService;
import com.qf.utils.RedisUtils;

@Service
public class QuartzRankServiceImpl implements QuartzRank {
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private RankService rankService;

	@Override
	public void barkRank() {
		// 1,从redis查询上次备份的最大主键值
		List<Rank> list;
		Integer id=0;
		if (redisUtils.exists("ranks")) {
			String json=redisUtils.getLast("ranks");
			if (json.length()>0) {
				Rank rank=JSON.parseObject(json,Rank.class);//讲redis中的数据转成对象
				id=rank.getId();
			}
		}
		//2.查询数据库，从上一次备份的最大值开始查询
		list=rankService.selectById(id);
		if (list.size()>0) {
			for (Rank rank : list) {
				redisUtils.ladd("ranks", JSON.toJSONString(rank));//讲reids中的对象转成json数据
			}
		}
	}

}
