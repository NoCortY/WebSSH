package cn.objectspace.webssh.util;


import cn.objectspace.webssh.exception.ApiException;
import cn.objectspace.webssh.response.BaseResponse;
import cn.objectspace.webssh.response.ResultCode;
import cn.objectspace.webssh.response.SuccessResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RestUtil {
    private static final Logger log = LoggerFactory.getLogger(RestUtil.class);

    /**
     * 更新时间字段
     */
    public static final String UPDATE_TIME = "UPDATE_TIME";

    /**
     * 防止sql注入
     *
     * @param sql
     * @return
     */
    public static String transactSQLInjection(String sql) {
        return sql.replaceAll("([';]+|(--)+)", "");
    }


    /**
     * 接口调用正常的ResultBean
     *
     * @param total
     * @param data
     * @return
     */
    public static SuccessResponse getNormalResultBean(Integer dataSize, Integer total, Object data) {
        SuccessResponse resultBean = new SuccessResponse(ResultCode.NORMARL.getCode(),
                ResultCode.NORMARL.getSemantics(), total, dataSize, data);
        return resultBean;
    }

    /**
     * 接口调用正常的 BaseResponse
     *
     * @return
     */
    public static BaseResponse getNormalResultBean() {
        BaseResponse resultBean = new BaseResponse(ResultCode.NORMARL.getCode(),
                ResultCode.NORMARL.getSemantics());
        return resultBean;
    }

    /**
     * 校验日期格式
     *
     * @param dateStr
     */
    public static Date checkDateFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("时间格式错误");
            throw new ApiException(ResultCode.INVALID_PARAM.getCode(),
                    "请检查日期格式(需使用 [yyyy-MM-dd'T'HH:mm:ss.SSS'Z']这种格式的时间)");
        }
        return date;
    }

    /**
     * 校验日期格式
     *
     * @param dateStr
     */
    public static Date paseDateFromLongStr(String dateStr) {
        Date date = null;
        try {
            Long timestamp = Long.parseLong(dateStr);
            date = new Timestamp(timestamp);
        } catch (Exception e) {
            log.error("时间格式错误");
            throw new ApiException(ResultCode.INVALID_PARAM.getCode(),
                    "请检查日期格式(需使用时间戳格式的时间)");
        }
        return date;
    }


    /**
     * 获取POST请求中Body参数
     *
     * @param request
     * @return 字符串
     */
    public static String getRequestBody(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            ServletInputStream inputStream = request.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
            log.error("获取POST请求参数出错", e);
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("获取POST请求参数出错", e);
        }
        return sb.toString();
    }
}
