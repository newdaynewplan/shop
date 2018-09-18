package com.chengyu.manager.controller;

import com.chengyu.common.pojo.EasyUITreeNode;
import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(
            @RequestParam(value="id", defaultValue="0") Long parentId) {

        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public E3Result createCategory(Long parentId, String name) {
        E3Result result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }

}
