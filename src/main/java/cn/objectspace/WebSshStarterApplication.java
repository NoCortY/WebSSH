package cn.objectspace;

import cn.objectspace.webssh.WebSshPackage;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("cn.objectspace.webssh.dao")
@ServletComponentScan
@EnableSwagger2Doc
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"cn.objectspace"}, basePackageClasses = {WebSshPackage.class})
public class WebSshStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSshStarterApplication.class);
    }
}
