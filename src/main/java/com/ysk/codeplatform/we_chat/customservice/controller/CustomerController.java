package com.ysk.codeplatform.we_chat.customservice.controller;

import com.ysk.codeplatform.we_chat.customservice.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 描述
 *
 * @author Y.S.K
 * @date 2018/9/21 18:22
 */
@RestController
@Log4j2
public class CustomerController {
    @Autowired
    private CustomerService customService;

    @ApiOperation("客服接入")
    @PostMapping("customService")
    public String customService(HttpServletRequest request, @RequestBody String message) throws IOException {
        log.info("客服接入");
        if (customService.checkSignature(request)) {
            customService.sendCustomMessage(message);
            return "success";
        }
        return "success";
    }

    @ApiOperation("客服接入")
    @GetMapping("customService")
    public String customService(HttpServletRequest request) {
        log.info("客服接入");
        if (customService.checkSignature(request)) {
            return request.getParameter("echostr");
        }
        return null;
    }
}
