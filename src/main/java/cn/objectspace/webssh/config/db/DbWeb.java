package cn.objectspace.webssh.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiaoJL
 * @description TODO
 * @file DbWeb.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 20:49
 */
public class DbWeb {
    private TcpH2b tcp;
    private WebH2Db web;

    public TcpH2b getTcp() {
        return tcp;
    }

    public void setTcp(TcpH2b tcp) {
        this.tcp = tcp;
    }

    public WebH2Db getWeb() {
        return web;
    }

    public void setWeb(WebH2Db web) {
        this.web = web;
    }

    public static class TcpH2b {
        private boolean enable;
        private boolean allowOther;
        private int port;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getPort() {
            return port;
        }

        public boolean isAllowOther() {
            return allowOther;
        }

        public void setAllowOther(boolean allowOther) {
            this.allowOther = allowOther;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public static class WebH2Db {
        private boolean enable;
        private int port;
        private boolean allowOther;

        public boolean isEnable() {
            return enable;
        }

        public boolean isAllowOther() {
            return allowOther;
        }

        public void setAllowOther(boolean allowOther) {
            this.allowOther = allowOther;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
