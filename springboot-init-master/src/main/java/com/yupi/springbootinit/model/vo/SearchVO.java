package com.yupi.springbootinit.model.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yupi.springbootinit.common.PageRequest;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.model.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 搜索视图
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class SearchVO  implements Serializable {


    private static final long serialVersionUID = -708910218656150160L;

    List<UserVO> userVOList;

    List<PostVO> postList;

    List<Picture> pictureList;
}
