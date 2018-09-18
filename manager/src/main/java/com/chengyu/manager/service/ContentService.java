package com.chengyu.manager.service;


import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.pojo.TbContent;

import java.util.List;

public interface ContentService {

	E3Result addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
}
