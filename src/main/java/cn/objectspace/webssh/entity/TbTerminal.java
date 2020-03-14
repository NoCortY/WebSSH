package cn.objectspace.webssh.entity;

import java.io.Serializable;

/**
 * (TbTerminal)实体类
 *
 * @author makejava
 * @since 2020-03-14 12:37:22
 */
public class TbTerminal implements Serializable {
    private static final long serialVersionUID = 706762324647173095L;

    private String terminalId;
    private String userId;
    private String terminalIp;

    private String terminalTitle;
    private String terminalName;

    private Integer terminalPort;

    private Object terminalPass;

    private Integer status;

    public String getUserId() {
        return userId;
    }

    public String getTerminalTitle() {
        return terminalTitle;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public void setTerminalTitle(String terminalTitle) {
        this.terminalTitle = terminalTitle;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public Integer getTerminalPort() {
        return terminalPort;
    }

    public void setTerminalPort(Integer terminalPort) {
        this.terminalPort = terminalPort;
    }

    public Object getTerminalPass() {
        return terminalPass;
    }

    public void setTerminalPass(Object terminalPass) {
        this.terminalPass = terminalPass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}