package com.chengyu.search.service;


import com.chengyu.common.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String keyword, int page, int rows)  throws Exception;
}
