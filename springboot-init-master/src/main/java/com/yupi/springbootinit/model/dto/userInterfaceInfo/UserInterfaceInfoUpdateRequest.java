package com.yupi.springbootinit.model.dto.userInterfaceInfo;

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
public class UserInterfaceInfoUpdateRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 总数
     */
    private Integer totalNum;

    /**
     * 剩余数
     */
    private Integer leftNum;

    /**
     * 0-正常 1-禁用
     */
    private Integer status;


    private static final long serialVersionUID = 1L;
}
