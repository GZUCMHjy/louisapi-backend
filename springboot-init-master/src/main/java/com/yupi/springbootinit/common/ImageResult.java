package com.yupi.springbootinit.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/15 21:52
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResult {
    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("copyright")
    private String copyright;

    @JsonProperty("copyrightlink")
    private String copyrightlink;
}
