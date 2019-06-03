package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.UserVo;


@Service
public class UserService {
	
	public UserService() {
		System.out.println("userService 생성!");
	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public Boolean existId(String id) {
		UserVo vo = userDao.getById(id);
		return vo != null;
	}
	
	public Boolean join(UserVo userVo, String url) {
		userDao.insert(userVo);
		blogDao.insert(new BlogVo(userVo.getId(), url ));
		return categoryDao.insertDefault(userVo.getId());
	}
	
	public UserVo getUser(UserVo userVo) {
		return userDao.getByVo(userVo);
	}
	
}
