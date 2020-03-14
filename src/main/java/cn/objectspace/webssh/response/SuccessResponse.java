package cn.objectspace.webssh.response;


/**
 * @author LiaoJL
 * @description TODO 访问成功响应信息
 * @file SuccessResponse.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 14:24
 */
public class SuccessResponse extends BaseResponse {
    public SuccessResponse(Integer resultCode, String resultMsg, Integer total, Integer dataSize) {
        super(resultCode, resultMsg);
        this.total = total;
        this.dataSize = dataSize;
    }

    public SuccessResponse(ResultCode resultCode, Integer total, Integer dataSize) {
        super(resultCode);
        this.total = total;
        this.dataSize = dataSize;
    }

    public SuccessResponse(Integer resultCode, String resultMsg, Object data, Integer total, Integer dataSize) {
        super(resultCode, resultMsg, data);
        this.total = total;
        this.dataSize = dataSize;
    }

    /**
     * 记录总数
     */
    private Integer total;

    /**
     * 当前返回总数
     */
    private Integer dataSize;


    /**
     * 成功响应*
     *
     * @param code     响应码
     * @param message  提示信息
     * @param total    总条数
     * @param dataSize 当前返回的条数
     * @param data
     * @return
     */
    public SuccessResponse(Integer code, String message, Integer total, Integer dataSize, Object data) {
        super(code, message, data);
        this.resultCode = code;
        this.resultMsg = message;
        this.total = total;
        this.dataSize = dataSize;
    }

    public SuccessResponse(Integer resultCode, String resultMsg, Integer total) {
        super(resultCode, resultMsg);
        this.total = total;
    }

    public SuccessResponse(Integer resultCode, String resultMsg, Object data, Integer total) {
        super(resultCode, resultMsg, data);
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }
}
