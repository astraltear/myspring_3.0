package com.audien.b2c.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.audien.b2c.domain.MemberVO;
import com.audien.b2c.persist.MemberDAO;
import com.audien.b2c.persist.SqlMapperDAO;
import com.audien.common.db.DataSourceType;
import com.audien.common.db.ReadOnlyConnection;

@Service
public class SqlMapperService {

	@Inject
	private SqlMapperDAO sqlMapperDAO;
	
	public String getTime() {
		return sqlMapperDAO.getTime();
	}

	public List selectBasic(MemberVO memberVO) {
		return sqlMapperDAO.selectBasic(memberVO);
	}

	public void multiInsert(List<MemberVO> list) {
		sqlMapperDAO.multiInsert(list);
	}
	public void multiInsertAI(List<MemberVO> list) {
		sqlMapperDAO.multiInsertAI(list);
	}	
	
	public void selectKeyInsert(MemberVO memberVO) {
		sqlMapperDAO.selectKeyInsert(memberVO);
	}	
	
	public List selectIF(MemberVO memberVO) {
		return sqlMapperDAO.selectIF(memberVO);
	}
	
	public List selectChoose(MemberVO memberVO) {
		return sqlMapperDAO.selectChoose(memberVO);
	}	
	
	public List selectWhere(MemberVO memberVO) {
		return sqlMapperDAO.selectWhere(memberVO);
	}	
	
	public List selectTrim(MemberVO memberVO) {
		return sqlMapperDAO.selectTrim(memberVO);
	}

	public int updateIfNecessary(MemberVO memberVO) {
		return sqlMapperDAO.updateIfNecessary(memberVO);
	}
	
	public List selectForeach(List<String> arrList) {
		return sqlMapperDAO.selectForeach(arrList);
	}
}
