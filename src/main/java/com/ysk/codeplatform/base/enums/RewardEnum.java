package com.ysk.codeplatform.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述
 *
 * @author Y.S.K
 * @date 2018/9/19 16:12
 */
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RewardEnum {
    GZH("GZH"),
    ;

    private String type;

    public static RewardEnum get(String type) {
        for (RewardEnum value : RewardEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
