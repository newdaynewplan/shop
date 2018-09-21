package com.chengyu.manager.controller;

import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.feign.SearchFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导入商品数据到索引库
 * <p>Title: SearchItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 * @version 1.0
 */
@Controller
public class SearchItemController {

	@Autowired
	private SearchFeign searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList() {
		E3Result e3Result = searchItemService.importAllItems();
		return e3Result;
	}
}
