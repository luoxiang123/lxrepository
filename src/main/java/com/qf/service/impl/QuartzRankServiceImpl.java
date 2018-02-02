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
		// 1,��redis��ѯ�ϴα��ݵ��������ֵ
		List<Rank> list;
		Integer id=0;
		if (redisUtils.exists("ranks")) {
			String json=redisUtils.getLast("ranks");
			if (json.length()>0) {
				Rank rank=JSON.parseObject(json,Rank.class);//��redis�е�����ת�ɶ���
				id=rank.getId();
			}
		}
		//2.��ѯ���ݿ⣬����һ�α��ݵ����ֵ��ʼ��ѯ
		list=rankService.selectById(id);
		if (list.size()>0) {
			for (Rank rank : list) {
				redisUtils.ladd("ranks", JSON.toJSONString(rank));//��reids�еĶ���ת��json����
			}
		}
	}

}
