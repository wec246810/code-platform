package com.ysk.codeplatform.we_chat.customservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysk.codeplatform.we_chat.customservice.config.Customer;
import com.ysk.codeplatform.we_chat.customservice.model.PicMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 描述
 *
 * @author Y.S.K
 * @date 2018/9/21 18:23
 */
@Service
@Log4j2
public class CustomerService {
    private final String PICTURE = "shareImg.jpg";

    @Autowired
    private ObjectMapper objectMapper;
    //    @Autowired
//    private CustomMapper customMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> stringStringValueOperations;

    /**
     * 检查签名
     *
     * @param request
     * @return
     */
    public boolean checkSignature(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        List<String> params = new ArrayList<String>(3);
        params.add(Customer.CUSTOMER_TOKEN);
        params.add(timestamp);
        params.add(nonce);
        params.sort(Comparator.naturalOrder());
        String temp = encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            log.info("signature verification true");
            return true;
        }
        log.info("signature verification false");
        return false;
    }


    public String getWxAccessToken() {
        String accessToken = stringStringValueOperations.get("access_token");
        if (accessToken == null) {
            //去微信端获取
            String tokenUrl = String.format(Customer.TOKEN_URL + "?appid=%s&secret=%s&grant_type=client_credential", Customer.APP_ID, Customer.APP_SECRET);
            Map<String, String> map = restTemplate.getForObject(tokenUrl, Map.class);
            if (map.get("errmsg") != null) {
                return null;
            }
            accessToken = map.get("access_token");
            stringStringValueOperations.set("access_token", accessToken, 100 * 60, TimeUnit.SECONDS);
        }
        return accessToken;
    }

    public void upload() throws IOException {
        ClassPathResource file = new ClassPathResource(PICTURE);
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<String, Object>();
        postParameters.add("file", file);
        String s = restTemplate.postForObject(String.format(Customer.SEND_MEDIA + "?access_token=%s&type=image", getWxAccessToken()), postParameters, String.class);
        Map<String, Object> map = objectMapper.readValue(s, Map.class);
        if (map.get("media_id") != null) {
            stringStringValueOperations.set("mediaIdKey", map.get("media_id").toString());
        }
    }


//    public void acceptMessage(String message) throws Exception {
//        Map<String, String> map = JSONObject.parseObject(message, Map.class);
//        String openid = map.get("FromUserName");
//        String AccessToken = getWxAccessToken();
//        String mediaId = stringStringValueOperations.get("mediaIdKey");
//        String url = Customer.SEND_MSG + "?access_token=" + AccessToken;
//        JSONObject jsonObject = new JSONObject();
//        jsonObject  .put("touser", openid);
//        jsonObject .put("msgtype", "image");
//        jsonObject .put("image", new JSONObject().put("media_id", mediaId));
//        String s = HttpUtil.post(url, jsonObject.toJSONString());
//    }

    public void sendCustomMessage(String message) throws IOException {
        log.info("sendCustomMessage");

        Map<String, String> map = objectMapper.readValue(message, Map.class);
        log.info(map);
        String openid = map.get("FromUserName");
        String content = Optional.ofNullable(map.get("Content")).orElse(map.get("PicUrl"));
        //用户消息发送到数据库
        if (content != null) {
//            customMapper.insert(Custom.of().setContent(content).setCreateTime(LocalDateTime.now()).setOpenId(openid));
        }
        String AccessToken = getWxAccessToken();
        log.info("AccessToken :{}", AccessToken);
        String mediaId = stringStringValueOperations.get("mediaIdKey");

        String url = Customer.SEND_MSG + "?access_token=" + AccessToken;

        restTemplate.postForObject(URI.create(url),
                PicMessage.of().setImage(PicMessage.ImageBean.of().setMedia_id(mediaId)).setMsgtype("image").setTouser(openid),
                String.class);
    }

    private static String encode(String str) {
        try {
            if (str != null) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(str.getBytes("UTF-8"));
                return getFormattedText(messageDigest.digest());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
