package com.ysk.codeplatform.we_chat.customservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* Created by Mybatis Generator 2018/09/21
*/
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Custom {
    private Integer id;

    private String openId;

    private LocalDateTime createTime;

    private String content;
}