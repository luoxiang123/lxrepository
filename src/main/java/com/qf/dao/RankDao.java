package com.qf.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.qf.pojo.Rank;

public interface RankDao extends JpaRepository<Rank, Integer>, JpaSpecificationExecutor<Rank> {
	// 修改,条件为组号 修改的是分数
	@Modifying
	@Query("update Rank set score=?1 where groupNo=?2")
	public boolean update(int score, int groupNo);
	
	public List<Rank> findByProjectNameLike(String name);
}
