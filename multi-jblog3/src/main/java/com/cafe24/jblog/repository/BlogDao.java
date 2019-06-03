package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BlogVo vo) {
		return 1 == sqlSession.insert("blog.insert", vo);
	}
	
	public BlogVo get(String blogId) {
		return sqlSession.selectOne("blog.get", blogId);
	}
	
	public Boolean updateBlog(String blogId, String url, String blogTitle) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blogId", blogId);
		map.put("url", url);
		map.put("title", blogTitle);
		return 1 == sqlSession.update("blog.updateLogo",map);
	}
}
