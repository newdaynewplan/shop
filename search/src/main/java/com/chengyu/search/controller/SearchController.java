package com.chengyu.search.controller;

import com.chengyu.common.pojo.SearchResult;
import com.chengyu.common.utils.E3Result;
import com.chengyu.search.service.SearchItemService;
import com.chengyu.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品搜索Controller
 * <p>Title: SearchController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class SearchController {

	private static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;

	@Autowired
	private SearchItemService searchItemService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;

	@RequestMapping("/search.html")
	public String searchItemList(String keyword,
                                 @RequestParam(defaultValue="1") Integer page, Model model) throws Exception {
		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		//查询商品列表
		SearchResult searchResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		//把结果传递给页面
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("recourdCount", searchResult.getRecordCount());
		model.addAttribute("itemList", searchResult.getItemList());
		//异常测试
//		int a = 1/0;
		//返回逻辑视图
		return "search";
	}

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importAllItems() {
		E3Result e3Result = searchItemService.importAllItems();
		logger.info("导入索引成功！");
		return e3Result;
	}
}
