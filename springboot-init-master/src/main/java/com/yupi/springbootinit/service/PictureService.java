package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.entity.Picture;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/29 15:33
 */
public interface PictureService {
    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);
}
