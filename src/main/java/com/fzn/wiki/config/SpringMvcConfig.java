package com.fzn.wiki.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author FZN
 * @date 2022/04/10 16:34
 * @description
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

/*    @Resource
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                // 拦截所有的请求
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }*/
}