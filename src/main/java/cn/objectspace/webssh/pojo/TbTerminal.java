package cn.objectspace.webssh.pojo;

import java.io.Serializable;

/**
 * (TbTerminal)实体类
 *
 * @author makejava
 * @since 2020-03-14 10:40:15
 */
public class TbTerminal implements Serializable {
    private static final long serialVersionUID = -73385118121783161L;

    private String terminalId;

    private String terminalName;

    private String terminalIp;

    private Integer terminalPort;

    private String terminalPass;

    private Integer status;


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

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public Integer getTerminalPort() {
        return terminalPort;
    }

    public void setTerminalPort(Integer terminalPort) {
        this.terminalPort = terminalPort;
    }

    public String getTerminalPass() {
        return terminalPass;
    }

    public void setTerminalPass(String terminalPass) {
        this.terminalPass = terminalPass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}