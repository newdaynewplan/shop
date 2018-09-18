package com.chengyu.manager.service;


import com.chengyu.common.pojo.EasyUITreeNode;
import com.chengyu.common.utils.E3Result;

import java.util.List;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);
	E3Result addContentCategory(long parentId, String name);
}
