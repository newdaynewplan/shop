package com.chengyu.manager.controller;

import com.chengyu.common.pojo.EasyUIDataGridResult;
import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.pojo.TbItem;
import com.chengyu.manager.pojo.TbItemDesc;
import com.chengyu.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
	public TbItem getItemById(@PathVariable(value = "itemId") Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping(value = "/itemDesc/{itemId}", method = RequestMethod.GET)
	public TbItemDesc getItemDescById(@PathVariable(value = "itemId") Long itemId) {
		TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		return itemDesc;
	}

	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		//调用服务查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	public E3Result saveItem(TbItem item, String desc) {
		E3Result result = itemService.addItem(item, desc);
		return result;
	}

	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	public E3Result deleteItem(String ids) {
		String[] split = ids.split(",");
		E3Result result = itemService.deleteItem(split);
		return result;
	}

}
