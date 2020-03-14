package cn.objectspace.webssh.config;


import cn.objectspace.webssh.config.db.DbWeb;
import com.google.common.collect.Lists;
import org.h2.server.Service;
import org.h2.server.web.WebServer;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LiaoJL
 * @description TODO
 * @file InitProject.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/13 22:03
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ProjectCommonConfig implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(ProjectCommonConfig.class);
    @Autowired
    private DbWeb dbWeb;

    @Bean
    @ConfigurationProperties(prefix = "system.h2")
    public DbWeb dbWeb() {
        return new DbWeb();
    }

    /**
     * 启动 H2 数据库
     */
    @DependsOn({"dbWeb"})
    @PostConstruct
    public void startH2ServerMode() {
        try {
            if (dbWeb.getTcp().isEnable()) {
                int port = dbWeb.getTcp().getPort();
                List<String> args = Lists.newArrayList("-tcpPort", port + "");
                if (dbWeb.getTcp().isAllowOther()) {
                    args.add("-tcpAllowOthers");
                }
                String[] array = args.toArray(new String[args.size()]);
                Server tcpServer = Server.createTcpServer(array).start();
                if (tcpServer.isRunning(true)) {
                    log.info("Tcp H2 server was started and is running.");
                } else {
                    log.error("Could not start TCP H2 server.");
                }
            }
            if (dbWeb.getWeb().isEnable()) {
                int port = dbWeb.getWeb().getPort();
                List<String> args = Lists.newArrayList("-webPort", port + "");
                if (dbWeb.getWeb().isAllowOther()) {
                    args.add("-webAllowOthers");
                }
                String[] array = args.toArray(new String[args.size()]);
                Server webServer = Server.createWebServer(array).start();
                if (webServer.isRunning(true)) {
                    log.info("Web H2 server was started and is running.");
                } else {
                    log.error("Could not start Web H2 server.");
                }
            }

        } catch (SQLException e) {
            throw new ApplicationContextException("Failed to start  H2 server: ", e);
        }
    }


}
