package com.chengyu.portal.feign.fallback;

import com.chengyu.portal.feign.ManagerFeign;
import com.chengyu.portal.pojo.TbContent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerFeignFallback implements ManagerFeign {
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        System.out.println("远程调用失败！");
        return null;
    }
}
