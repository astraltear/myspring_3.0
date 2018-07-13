package com.audien.b2c.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.audien.b2c.persist.MemberDAO;
import com.audien.common.db.DataSourceType;
import com.audien.common.db.ReadOnlyConnection;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO memberDAO;

	@Transactional(readOnly = true)
	@ReadOnlyConnection(DataSourceType.SLAVE)
	public List readMemberAllSlave() {
		return memberDAO.readMemberAllSlave();
	}

	public List readMemberAllMaster() {
		return memberDAO.readMemberAllMaster();
	}
	
	
}
