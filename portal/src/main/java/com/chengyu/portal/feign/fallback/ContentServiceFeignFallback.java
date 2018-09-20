package com.chengyu.portal.feign.fallback;

import com.chengyu.portal.feign.ContentServiceFeign;
import com.chengyu.portal.pojo.TbContent;

import java.util.List;

public class ContentServiceFeignFallback implements ContentServiceFeign {
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        System.out.println("远程调用失败！");
        return null;
    }
}
