package com.yupi.springbootinit.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/3 22:26
 */
@Data
public class InterfaceInfoInvoke implements Serializable {
    /**
     * 接口id
     */
    private Long id;
    /**
     * 接口参数
     */
    private String userRequestParams;
    private static final long  serialVersionUID = 1L;
}
