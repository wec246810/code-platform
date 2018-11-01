package com.ysk.codeplatform;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Log4j2
public class CodePlatformApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CodePlatformApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();

        log.debug("Spring 加载启动Bean个数{}", beanNames.length);
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) ctx.getBean("tomcatServletWebServerFactory");
            int port = tomcatServletWebServerFactory.getPort();
            String contextPath = tomcatServletWebServerFactory.getContextPath();
            String path = "http://" + host + ":" + port + contextPath + "/";
            System.out.println("\n<------------------------------------------  " + path + "  ------------------------------------------>\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
