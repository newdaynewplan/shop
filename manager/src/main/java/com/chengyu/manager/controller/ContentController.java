package com.chengyu.manager.controller;

import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.pojo.TbContent;
import com.chengyu.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public E3Result addContent(TbContent content) {
        E3Result result = contentService.addContent(content);
        return result;
    }

    @RequestMapping("/content/list")
    @ResponseBody
    public List<TbContent> getContentListByCid(@RequestParam(value = "cid") long cid) {
        List<TbContent> list = contentService.getContentListByCid(cid);
        return list;
    }
}

