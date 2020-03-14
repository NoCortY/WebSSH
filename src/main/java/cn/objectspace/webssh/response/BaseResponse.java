package cn.objectspace.webssh.response;

/**
 * @author LiaoJL
 * @description TODO 基础响应
 * @file BaseResponse.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 14:24
 */
public class BaseResponse {

    /**
     * 响应编码{@link ResultCode}
     */
    protected Integer resultCode;
    /**
     * 提示信息
     */
    protected String resultMsg;


    /**
     * 返回数据
     */
    private Object data;

    public BaseResponse(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public BaseResponse(ResultCode resultCode) {
        this.resultCode = resultCode.getCode();
        this.resultMsg = resultCode.getSemantics();
    }

    public BaseResponse(Integer resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public BaseResponse setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode.getCode();
        this.resultMsg = resultCode.getSemantics();
        return this;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
