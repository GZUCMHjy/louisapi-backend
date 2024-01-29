package com.yupi.springbootinit.model.dto.search;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/29 16:27
 */
@Data
public class SearchRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 6820582724354424148L;

    /**
     * 搜索词
     */
    private String searchText;
}
