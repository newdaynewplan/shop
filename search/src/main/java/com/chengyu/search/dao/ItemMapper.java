package com.chengyu.search.dao;


import com.chengyu.common.pojo.SearchItem;

import java.util.List;

public interface ItemMapper {

	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
