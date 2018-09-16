package com.chengyu.manager.service;


import com.chengyu.common.pojo.EasyUIDataGridResult;
import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.pojo.TbItem;
import com.chengyu.manager.pojo.TbItemDesc;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	E3Result addItem(TbItem item, String desc);
	E3Result deleteItem(String[] split);
	TbItemDesc getItemDescById(long itemId);
}
