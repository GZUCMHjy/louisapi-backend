package com.yupi.springbootinit.model.dto.picture;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/29 15:30
 */
@Data
public class PictureQueryRequest extends PageRequest {
    private String searchText;
}
