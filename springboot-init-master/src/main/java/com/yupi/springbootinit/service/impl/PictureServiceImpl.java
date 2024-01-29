package com.yupi.springbootinit.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    /**
     * 图片搜索
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        long  current = (pageNum - 1) * pageSize;
        // 采用动态替换获取
        String url = String.format("https://cn.bing.com/images/search?q=%sform=HDRSC2&first=%s",searchText,current);
        Document document = null;
        try{
            document = Jsoup.connect(url).get();
        }catch (IOException e){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据获取失败");
        }

        // System.out.println(document);
        Elements elements = document.select(".iuscp.isv");
        List<Picture> pictures = new ArrayList<>();
        for(Element e : elements){
            //System.out.println(e);
            // json格式字符串
            String m = e.select(".iusc").get(0).attr("m");
            Map<String,Object> map = JSONUtil.toBean(m, Map.class);
            String murl =(String) map.get("murl");
            String title = e.select(".inflnk").get(0).attr("aria-label");
            Picture picture = new Picture();
            picture.setUrl(murl);
            picture.setTitle(title);
            pictures.add(picture);
            if(pictures.size() >= pageSize){
                break;
            }
        }
        Page<Picture> picturePage = new Page<>(pageNum,pageSize);
        return picturePage.setRecords(pictures);
    }
}
