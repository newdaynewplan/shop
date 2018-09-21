package com.chengyu.item.feign.fallback;

import com.chengyu.item.feign.ManagerFeign;
import com.chengyu.item.pojo.TbItem;
import com.chengyu.item.pojo.TbItemDesc;

public class ManagerFeignFallback implements ManagerFeign {
    @Override
    public TbItem getItemById(Long itemId) {
        return null;
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        return null;
    }
}
