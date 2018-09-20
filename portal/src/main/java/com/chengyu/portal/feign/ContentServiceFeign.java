package com.chengyu.portal.feign;

import com.chengyu.portal.feign.fallback.ContentServiceFeignFallback;
import com.chengyu.portal.pojo.TbContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "manager",fallback = ContentServiceFeignFallback.class )
public interface ContentServiceFeign {

    @RequestMapping("/content/list")
    @ResponseBody
    public List<TbContent> getContentListByCid(@RequestParam(value = "cid")long cid);
}
