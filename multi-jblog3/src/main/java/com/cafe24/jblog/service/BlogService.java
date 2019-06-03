package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {

	private static final String SAVE_PATH = "/jblog-uploads";
	
	private static final String URL = "/logo";
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	// 블로그 정보 get
	public BlogVo get(String blogId) {
		return blogDao.get(blogId);
	}
	
	//카테고리 리스트 get
	public List<CategoryVo> getCategoryList(String blogId) {
		return categoryDao.getList(blogId);
	}
	//카테고리 추가
	public Boolean addCategory(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}
	//카테고리 삭제
	public Boolean removeCategory(CategoryVo categoryVo) {
		postDao.delete(categoryVo.getNo());
		return categoryDao.delete(categoryVo);
	}
	
	// 블로그 첫화면 일때 첫 카테고리 보여줌 
	public List<PostVo> getFirstCategoryList(String blogId) {
		return postDao.getFirstCategoryList(blogId);
	}
	
	// 카테고리 포스트 목록
	public List<PostVo> getPostListByCategory(Long cateNo) {
		return postDao.getPostListByCategory(cateNo);
	}
	
	// 포스트 하나 get  
	public PostVo getPostWithCategory(Long cateNo, Long postNo) {
		return postDao.getPostWithCategory(cateNo, postNo);
	}
	
	// 로그파일 업로드 
	public void restoreBlogInfo(MultipartFile multipartFile, String blogId, String blogTitle) {
		String url = "";
		
		try {
		
			if(multipartFile.isEmpty()) {
				return;
			}
			
			String originalFileName = multipartFile.getOriginalFilename();
			
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			
			String saveFileName = generateSaveFileName(ext);
			
			
			byte[] fileData = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			
			os.write(fileData);
			os.close();
			
			url = URL + "/" + saveFileName;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		blogDao.updateBlog(blogId,url,blogTitle);
		
	}
	
	// 파일 업로드 시 이름 생성
	public String generateSaveFileName(String ext) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		
		fileName += "." + ext;
		
		return fileName;
	}
	
	//글작성
	public Boolean writePost(PostVo postVo) {
		return postDao.insert(postVo);
	}
	
}
