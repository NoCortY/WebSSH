package cn.objectspace.webssh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TbUser)实体类
 *
 * @author makejava
 * @since 2020-03-14 12:38:22
 */
public class TbUser implements Serializable {
    private static final long serialVersionUID = -74457481994433141L;
    
    private String id;
    
    private String userName;
    
    private String userPass;
    
    private Date addTime;
    
    private Date updateTime;
    
    private Integer adminRole;
    
    private String ext;
    
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(Integer adminRole) {
        this.adminRole = adminRole;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}