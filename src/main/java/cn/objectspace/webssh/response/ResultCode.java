package cn.objectspace.webssh.response;

public enum ResultCode {


    /**
     * 正常
     */
    NORMARL(200, "正常"),
    /**
     * 错误的客户端请求，包括缺少参数、参数格式错误
     */
    INVALID_PARAM(400, "参数无效"),
    /**
     * 没有通过身份认证，包括应用标识错误、应用密钥不正确、验证签名失败
     */
    ID_AUTHENTICATE_FAILD(401, "身份认证失败"),
    /**
     * 用户通过了身份验证，但是不具有访问资源所需的权限
     */
    INTERFACE_NO_AUTH(403, "无访问权限"),
    /**
     * 请求资源不存在，包括请求url不正确
     */
    INVALID_URL(404, "请求url不正确"),
    /**
     * 用户已经通过身份验证，但是所用的 HTTP 方法不在他的权限之内
     */
    HTTP_METHOD_NO_AUTH(405, "访问的Http方法未授权"),
    /**
     * 客户端请求次数超过限额，包括请求次数过多、请求被重放
     */
    REQUEST_TOO_MANY(429, "请求次数过多"),
    /**
     * 接口服务内部错误，具体错误见错误信息
     */
    SERVER_ERROR(500, "接口服务内部错误"),
    /**
     * 服务器不具备完成请求的功能，如尚未实施的接口
     */
    INTERFACE_NOT_EXIST(501, "不具备完成改请求的功能"),
    /**
     * 查询用户表操作
     */
    NOT_EXIST_USER(400001, "用户不存在"),
    /**
     * 用户重复激活
     */
    USER_REPEAT_ACTIVATION(400002, "用户激活接口-激活失败，用户已激活"),
    /**
     * 修改密码原密码错误
     */
    UPDATE_USER_PWD_OLDER_PWD_ERROR(400003, "修改密码接口-修改密码失败，原密码错误"),
    /**
     * 重置密码，验证码错误
     */
    RESET_PWD_VALIDATE_CODE_ERROR(400004, "重置密码-重置密码失败，校验码错误"),

    /**
     * 校验验证码，验证码错误
     */
    VERIFICATION_VERIFICATION_CODE_ERROR(400005, "校验验证码接口-校验失败，验证码错误");

    private int code;
    private String semantics;

    private ResultCode(int code, String semantics) {
        this.code = code;
        this.semantics = semantics;
    }

    public int getCode() {
        return code;
    }

    public String getSemantics() {
        return semantics;
    }
}
