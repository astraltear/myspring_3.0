package com.audien.b2c.persist;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.audien.b2c.mapper.MemberMapper";

	public List readMemberAllSlave() {
		return sqlSession.selectList(namespace + ".readMemberAll");
	}
	
	public List readMemberAllMaster() {
		return sqlSession.selectList(namespace + ".readMemberAll");
	}
}
