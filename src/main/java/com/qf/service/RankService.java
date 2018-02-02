package com.qf.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.qf.pojo.Rank;

public interface RankService {
	// ����
	public boolean save(Rank rank);

	// �޸�
	public boolean update(Rank rank);

	public boolean update(int score, int groupNo);

	// ɾ��
	public boolean delete(Integer id);

	// ��ѯ
	public Page<Rank> getByPage(int page,int size);

	// ��ѯ����
	public Rank getById(Integer id);
	
	public List<Rank> findByProjectNameLike(String name);
	
	public List<Rank> selectById(Integer id);
}
