package com.biz.shop.persistence;

import com.biz.shop.model.DeptVO;

public interface DeptDao extends GeneriDao<DeptVO, String> {

	public String maxDCode();

}
