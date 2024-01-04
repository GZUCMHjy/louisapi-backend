package com.yupi.springbootinit.model.dto.interfaceinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author louis
 * @version 1.0
 * @date 2023/12/23 14:38
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 描述
     */
    private String desciption;

    /**
     * 请求类型
     */
    private String method;
    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求地址
     */
    private String url;

    private static final long serialVersionUID = 1L;
}
