package com.audien.b2c.persist;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audien.b2c.domain.MemberVO;

@Repository
public class SqlMapperDAO {
	
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.astraltear.mapper.examMapper";
	
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	public List selectBasic(MemberVO memberVO) {
		return sqlSession.selectList(namespace + ".selectBasic",memberVO);
	}

	public void multiInsert(List<MemberVO> list) {
		sqlSession.insert(namespace+".multiInsert", list);
	}

	public void multiInsertAI(List<MemberVO> list) {
		sqlSession.insert(namespace+".multiInsertAI", list);
	}	

	public void selectKeyInsert(MemberVO memberVO) {
		sqlSession.insert(namespace+".selectKeyInsert", memberVO);
	}

	public List selectIF(MemberVO memberVO) {
		return sqlSession.selectList(namespace + ".selectIF",memberVO);
	}

	public List selectChoose(MemberVO memberVO) {
		return sqlSession.selectList(namespace + ".selectChoose",memberVO);
	}


	public List selectWhere(MemberVO memberVO) {
		return sqlSession.selectList(namespace + ".selectWhere",memberVO);
	}

	public List selectTrim(MemberVO memberVO) {
		return sqlSession.selectList(namespace + ".selectTrim",memberVO);
	}	
	

	public int updateIfNecessary(MemberVO memberVO) {
		return sqlSession.update(namespace + ".updateIfNecessary",memberVO);
	}

	public List selectForeach(List<String> list) {
		return sqlSession.selectList(namespace + ".selectForeach",list);
	}	
}
