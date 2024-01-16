package com.yupi.springbootinit.model.vo;//package com.yupi.springbootinit.model.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yuapi.common.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * 接口信息封装视图
 *
 * @author louis
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo implements Serializable {
    /**
     * 调用总次数
     */
    private Integer totalNum;
    private static final long serialVersionUID = 1L;
}
