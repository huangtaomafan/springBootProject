package com.springboot.learn;

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
 * Swagger2构建RESTful APIs文档
 * @author liuzq
 * @version $Id: Swagger2.java, v 0.1 2016年5月25日 上午10:38:53 liuzq Exp $
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.springboot.learn.web"))
            .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
            .description("更多Spring Boot相关文章请关注：http://my.oschina.net/u/2613313/blog")
            .termsOfServiceUrl("http://my.oschina.net/u/2613313/blog").contact("刘志强").version("1.0")
            .build();
    }

}
