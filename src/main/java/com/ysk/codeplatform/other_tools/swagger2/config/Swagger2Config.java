package com.ysk.codeplatform.other_tools.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述
 *
 *   <!--整合 swagger-->
 *         <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger2</artifactId>
 *             <version>2.9.2</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger-ui</artifactId>
 *             <version>2.9.2</version>
 *         </dependency>
 *
 * @author Y.S.K
 * @date 2018/10/18 10:58
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.lsj.xcjfs"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("斗鲲赛小游戏后端api接口")
                .description("斗鲲赛小游戏后端api接口")
                .version("1.0")
                .build();
    }
}

