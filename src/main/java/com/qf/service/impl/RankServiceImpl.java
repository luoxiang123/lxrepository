package com.qf.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.qf.dao.RankDao;
import com.qf.pojo.Rank;
import com.qf.service.RankService;

@Service
@Transactional
public class RankServiceImpl implements RankService {
	@Autowired
	private RankDao rankdao;

	@Override
	public boolean save(Rank rank) {
		// TODO Auto-generated method stub
		try {
			rankdao.save(rank);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Rank rank) {
		// TODO Auto-generated method stub
		try {
			rankdao.update(rank.getScore(), rank.getGroupNo());
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(int score, int groupNo) {
		// TODO Auto-generated method stub
		try {
			rankdao.update(score, groupNo);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			rankdao.delete(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Page<Rank> getByPage(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page - 1, size);
		return rankdao.findAll(pageable);
	}

	@Override
	public Rank getById(Integer id) {
		// TODO Auto-generated method stub
		return rankdao.findOne(id);
	}

	@Override
	public List<Rank> findByProjectNameLike(String name) {
		// TODO Auto-generated method stub
		return rankdao.findByProjectNameLike(name);
	}

	@Override
	public List<Rank> selectById(Integer id) {
		// TODO Auto-generated method stub
		Specification<Rank> specification=new Specification<Rank>() {
			@Override
			public Predicate toPredicate(Root<Rank> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.gt(root.get("id").as(Integer.class), id);
			}
		};
		return rankdao.findAll(specification);
	}

}
