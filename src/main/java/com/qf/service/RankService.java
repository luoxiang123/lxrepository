package com.qf.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.qf.pojo.Rank;

public interface RankService {
	// 新增
	public boolean save(Rank rank);

	// 修改
	public boolean update(Rank rank);

	public boolean update(int score, int groupNo);

	// 删除
	public boolean delete(Integer id);

	// 查询
	public Page<Rank> getByPage(int page,int size);

	// 查询单个
	public Rank getById(Integer id);
	
	public List<Rank> findByProjectNameLike(String name);
	
	public List<Rank> selectById(Integer id);
}
