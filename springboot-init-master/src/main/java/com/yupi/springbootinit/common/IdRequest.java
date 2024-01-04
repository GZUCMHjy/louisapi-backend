package com.yupi.springbootinit.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/2 11:31
 */
@Data

public class IdRequest implements Serializable {
    private static final long serialVersionUID = -829564073799664185L;
    private Long id;

}
