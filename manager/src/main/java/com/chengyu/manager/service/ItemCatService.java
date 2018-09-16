package com.chengyu.manager.service;


import com.chengyu.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getCatList(long parentId);
}
