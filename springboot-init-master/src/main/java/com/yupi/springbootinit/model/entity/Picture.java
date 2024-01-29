package com.yupi.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/29 15:17
 */
@Data
public class Picture implements Serializable {
    private static final long serialVersionUID = 4473334845302675835L;
    private String url;
    private String title;
}
