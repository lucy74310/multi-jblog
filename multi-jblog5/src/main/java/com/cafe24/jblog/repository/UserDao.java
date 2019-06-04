package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo getById(String id) {
		return sqlSession.selectOne("user.getById", id);
	}
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return 1 == count ;
	}
	public UserVo getByVo(UserVo vo) {
		return sqlSession.selectOne("user.getById", vo);
	}

}
