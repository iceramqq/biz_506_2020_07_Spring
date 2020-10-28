package com.biz.ems.service;

import java.util.List;

import com.biz.ems.model.EmsVO;

public interface EmsService {

	public List<EmsVO> selectAll();
	public EmsVO findById(long id);
	public int insert(EmsVO emsVO);
	public int update(EmsVO emsVO);
	public int delete(long id);
	
}
