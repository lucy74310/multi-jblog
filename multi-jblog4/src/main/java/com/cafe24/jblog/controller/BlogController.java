package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/{id:^(?!assets|logo).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 blog-main.jsp
	@RequestMapping({"","/" , "/{cateNo}", "/{cateNo}/{postNo}"})
	public String blog
	(	@PathVariable("id") String blogId,
		@PathVariable("cateNo") Optional<Long> cateNo,
		@PathVariable("postNo") Optional<Long> postNo,
		Model model,
		HttpServletRequest request
	) 
	{
		//블로그 정보 가져오기 
		BlogVo blogVo = blogService.get(blogId);
		if(blogVo == null ) return "redirect:/main";
		
		//카테고리 정보 가져오기
		List<CategoryVo> cateList = blogService.getCategoryList(blogId);
		List<PostVo> postList= null;
		PostVo postVo = null;
		
		if(!cateNo.isPresent() && !postNo.isPresent()) {
			postList = blogService.getFirstCategoryList(blogId);
			if(postList.size() > 0) {
				postVo = postList.get(0);
			} else {
				postVo = new PostVo();
				postVo.setTitle("등록된 글이 없습니다.");
			}
			
		} 
		
		if(cateNo.isPresent() && !postNo.isPresent()) {
			postList = blogService.getPostListByCategory(cateNo.get());
			if(postList.size() > 0) {
				postVo = postList.get(0);
			} else {
				postVo = new PostVo();
				postVo.setTitle("등록된 글이 없습니다.");
			}
		}
		
		if(cateNo.isPresent() && postNo.isPresent()) {
			postList = blogService.getPostListByCategory(cateNo.get());
			if(postList.size() > 0) {
				postVo = blogService.getPostWithCategory(cateNo.get(), postNo.get());
			} else {
				postVo = new PostVo();
				postVo.setTitle("등록된 글이 없습니다.");
			}
		}
		
		
		
		
		model.addAttribute("blog", blogVo);
		model.addAttribute("category", cateList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		
		
		return "blog/blog-main";
		
	}
	
	
	// 블로그 관리자페이지 blog-admin-{menu}.jsp
	@RequestMapping(value="/admin/{menu}", method=RequestMethod.GET)
	public String admin
	(
		@PathVariable("id") String blogId,
		@PathVariable("menu") String menu,
		Model model	
		
	) {
		
		BlogVo blogVo = blogService.get(blogId);
		
		if(blogVo == null ) {
			return "redirect:/main";
		}
		
		List<CategoryVo> cateList = blogService.getCategoryList(blogId);
		
		
		model.addAttribute("category", cateList);
		model.addAttribute("blog", blogVo);
		
		
		if("basic".equals(menu) || "category".equals(menu) || "write".equals(menu)) {
			return "blog/blog-admin-"+menu;
		}
		
		
		
		return "blog/blog-admin-basic";
	}
	
	
	//관리자페이지- 기본설정저장 
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic
	(
			@PathVariable("id") String blogId,
			@RequestParam("title") String blogTitle,
			@RequestParam("logo-file") MultipartFile multipartFile,
			
			Model model
	) {
		
		blogService.restoreBlogInfo(multipartFile, blogId, blogTitle);
		model.addAttribute("blog", blogService.get(blogId));
		
		
		return "blog/blog-admin-basic";
		
	}
	
	
	
	//관리자페이지- 카테고리 불러오기, 추가 삭제 ( ajax라서 blog정보 다시 넘겨주지 않아도 됨) 
	@ResponseBody
	@RequestMapping(value="/admin/category/{menu}", method=RequestMethod.POST)
	public JSONResult adminCategory
	(
		@PathVariable("id") String blogId,
		@PathVariable("menu") String menu,
		@ModelAttribute CategoryVo categoryVo
		
	) {
		
		if("add".equals(menu)) {
			blogService.addCategory(categoryVo);
		} else if("delete".equals(menu)) {
			blogService.removeCategory(categoryVo);
		}
		
		
		List<CategoryVo> cateList = blogService.getCategoryList(blogId);
		
		
		return JSONResult.success(cateList);
	}
	
	
	//관리자페이지 - 글작성
	@ResponseBody
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite
	(
			@PathVariable("id") String blogId,
			@ModelAttribute PostVo postVo
	) {
		
		//글 작성
		blogService.writePost(postVo);
		
		return "success";
	}

	
}

