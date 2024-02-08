package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yuapi.common.model.vo.UserVO;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.manager.SearchFacade;
import com.yupi.springbootinit.model.dto.search.SearchRequest;
import com.yupi.springbootinit.model.dto.user.UserQueryRequest;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.model.enums.SearchTypeEnum;
import com.yupi.springbootinit.model.vo.SearchVO;

import com.yupi.springbootinit.service.PictureService;
import com.yupi.springbootinit.service.UserService;
import io.reactivex.rxjava3.core.Completable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/29 16:24
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
    @Resource
    private PictureService pictureService;


    @Resource
    private UserService userService;

    @Resource
    private SearchFacade searchFacade;
    /**
     * 聚合搜索接口（无ES版 ）
     * @param searchRequest
     * @return
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody  SearchRequest searchRequest, HttpServletRequest request) {
//        String type = searchRequest.getType();
//        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
//        // 类型为空时，抛出异常
//        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
//        // 获取搜索词
//        String searchText = searchRequest.getSearchText();
//        // type为空时，搜索所有数据（采用并行查询）
//        if (searchTypeEnum == null) {
//            // 用户搜索
//            CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() -> {
//                UserQueryRequest userQueryRequest = new UserQueryRequest();
//                userQueryRequest.setUserName(searchText);
//                Page<UserVO> userVOPage = userService.listUserVOPage(userQueryRequest);
//                return userVOPage;
//            });
//            // 图片搜索
//            CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() -> {
//                Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);
//                return picturePage;
//            });
//            // 异步都进行完毕后执行join
//            CompletableFuture.allOf(userTask, pictureTask).join();
//            try {
//                Page<UserVO> userVOPage = userTask.get();
//                Page<Picture> picturePage = pictureTask.get();
//                SearchVO searchVO = new SearchVO();
//                searchVO.setUserList(userVOPage.getRecords());
//                searchVO.setPictureList(picturePage.getRecords());
//                return ResultUtils.success(searchVO);
//            } catch (Exception e) {
//                log.error("查询异常", e);
//                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询异常");
//            }
//        } else {
//            SearchVO searchVO = new SearchVO();
//            switch (searchTypeEnum) {
//                case PICTURE:
//                    Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);
//                    searchVO.setPictureList(picturePage.getRecords());
//                    break;
//                case USER:
//                    UserQueryRequest userQueryRequest = new UserQueryRequest();
//                    userQueryRequest.setUserName(searchText);
//                    Page<UserVO> userVOPage = userService.listUserVOPage(userQueryRequest);
//                    searchVO.setUserList(userVOPage.getRecords());
//                    break;
//                default:
//            }
//            return ResultUtils.success(searchVO);
//        }
        SearchVO searchVO = searchFacade.searchAll(searchRequest, request);
        return ResultUtils.success(searchVO);

    }
}
