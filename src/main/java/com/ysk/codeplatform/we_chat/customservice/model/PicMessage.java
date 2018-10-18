package com.ysk.codeplatform.we_chat.customservice.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author Y.S.K
 * @date 2018/9/21 20:00
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class PicMessage {
    /**
     * touser : OPENID
     * msgtype : image
     * image : {"media_id":"MEDIA_ID"}
     */

    private String touser;
    private String msgtype;
    private ImageBean image;

    @Data
    @Accessors(chain = true)
    @RequiredArgsConstructor(staticName = "of")
    public static class ImageBean {
        /**
         * media_id : MEDIA_ID
         */

        private String media_id;
    }
}
