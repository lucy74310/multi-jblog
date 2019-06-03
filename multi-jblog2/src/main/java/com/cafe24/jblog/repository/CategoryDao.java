package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertDefault(String id) {
		return 1 == sqlSession.insert("category.insertDefault",id);
	}
	
	public List<CategoryVo> getList(String id) {
		return sqlSession.selectList("category.getList", id);
	}
	
	public Boolean insert(CategoryVo categoryVo) {
		return 1 == sqlSession.insert("category.insert" , categoryVo);
	}
	
	public Boolean delete(CategoryVo categoryVo) {
		return 1 == sqlSession.delete("category.delete" , categoryVo);
	}
}
