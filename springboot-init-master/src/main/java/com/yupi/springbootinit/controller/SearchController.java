package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.model.dto.search.SearchRequest;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.model.vo.SearchVO;
import com.yupi.springbootinit.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/29 16:24
 */
@RestController
@RequestMapping("/search‘")
@Slf4j
public class SearchController {
    @Resource
    private PictureService pictureService;

    /**
     * 聚合搜索接口（无ES版 ）
     * @param searchRequest
     * @return
     */
    @PostMapping
    public BaseResponse<SearchVO> searchAll(@RequestBody  SearchRequest searchRequest){
        String searchText = searchRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);
        // 用户、帖子表 也是一样的
        // 比如 new UserQueryRequest().setUsername(searchText);
        // 调用userService.selectUser(new UserQueryRequest().setUsername(searchText))
        SearchVO searchVO = new SearchVO();
        searchVO.setPictureList(picturePage.getRecords());
        searchVO.setPostList(null);
        searchVO.setUserVOList(null);
        return ResultUtils.success(searchVO);
    }
}
