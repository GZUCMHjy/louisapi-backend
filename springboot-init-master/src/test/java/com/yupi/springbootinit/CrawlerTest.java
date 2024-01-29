package com.yupi.springbootinit;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yuapi.common.model.entity.User;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.model.entity.Post;
import com.yupi.springbootinit.service.PostService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/28 17:08
 */
@SpringBootTest
public class CrawlerTest {

    @Resource
    private PostService postService;
    @Test
    public void testFetchPicture() throws IOException {
        int current = 1;
        // 采用动态替换获取
        String url = String.format("https://cn.bing.com/images/search?q=高木form=HDRSC2&first=%d",current);
        Document document = Jsoup.connect(url).get();
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
        }
        //Elements select = document.select();
        for (Picture picture : pictures) {
            System.out.println(picture.getUrl());
        }

    }


    @Test
    public void testFetchPassage(){
        String json  = "{\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"reviewStatus\":1,\"current\":1}";
        String url = "https://www.code-nav.cn/api/post/list/page/vo";
        // 返回的其实json格式的数据（数据类型还是字符串）
        String result = HttpRequest.post(url)
                .body(json)
                .execute()
                .body();
        // 转换成Java对象（体力劳动）
        // 一般获取接口返回的json格式的数据，我们采用Map进行键值对映射
        Map<String,Object> map = JSONUtil.toBean(result,Map.class);
        System.out.println(map);
        // todo 校验返回的data数据code是否ok
        JSONObject data = (JSONObject) map.get("data");
        JSONArray records =(JSONArray) data.get("records");
        List<com.yupi.springbootinit.model.entity.Post> postList = new ArrayList<>();
        for (Object record : records) {
            // 强转为JSONObject
            JSONObject tempRecord = (JSONObject) record;
            // 获取数据里面的字段信息

            com.yupi.springbootinit.model.entity.Post post = new com.yupi.springbootinit.model.entity.Post();
            post.setTitle(tempRecord.getStr("title"));
            post.setContent(tempRecord.getStr("content"));
            JSONArray tags = (JSONArray)tempRecord.get("tags");
            List<String> tagList = tags.toList(String.class);
            post.setTags(JSONUtil.toJsonStr(tagList));
            //post.setUserId(1738422808888221697L);
            postList.add(post);
        }
        System.out.println(postList);
        // 批量插入（性能比循环插入要好）
        for (Post post : postList) {
            postService.save(post);
        }
        //postService.saveBatch(postList);
    }
}
