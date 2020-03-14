package cn.objectspace.webssh.config;

import cn.objectspace.webssh.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiaoJL
 * @description TODO
 * @file WebAppConfigurer.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 16:17
 */
public class WebAppConfigurer implements WebMvcConfigurer {
    /**
     * 登陆拦截器
     *
     * @return
     */
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * Configure view resolvers to translate String-based view names returned from
     * controllers into concrete {@link View}
     * implementations to perform rendering with.
     *
     * @param registry
     * @since 4.1
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/*.js", "/**/*.css", "/**/*.png", "/**/*.ttf", "/**/*.woff", "/**/*.jpg", "/", "/login", "/index.html")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/configuration/ui")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/security")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/**/favicon.ico")
        ;
    }
}
