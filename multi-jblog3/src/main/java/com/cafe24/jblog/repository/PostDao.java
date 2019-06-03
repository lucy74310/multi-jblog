package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean delete(Long no) {
		return 1 == sqlSession.delete("post.delete", no);
	}
	
	public Boolean insert(PostVo postVo) {
		return 1 == sqlSession.insert("post.insert", postVo);
	}
	
	//블로�?�??�면 �?카테고리 리스??
	public List<PostVo> getFirstCategoryList(String blogId) {
		return sqlSession.selectList("post.getFirstCategoryList", blogId);
	}
	
	public List<PostVo> getPostListByCategory(Long cateNo) {
		return sqlSession.selectList("post.getListByCategory", cateNo);
	}
	

	public PostVo getPostWithCategory(Long cateNo, Long postNo) {
		Map<String, Long> map = new HashMap<String,Long>();
		map.put("cateNo", cateNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne("post.getPostWithCategory", map);
	}
}
