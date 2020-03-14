package cn.objectspace.webssh.controller;

import cn.objectspace.webssh.config.db.DbWeb;
import cn.objectspace.webssh.entity.TbTerminal;
import cn.objectspace.webssh.entity.TbUser;
import cn.objectspace.webssh.interceptor.LoginInterceptor;
import cn.objectspace.webssh.response.BaseResponse;
import cn.objectspace.webssh.response.ErrorResponse;
import cn.objectspace.webssh.response.ResultCode;
import cn.objectspace.webssh.service.TbTerminalService;
import cn.objectspace.webssh.service.TbUserService;
import cn.objectspace.webssh.util.EncryptUtil;
import cn.objectspace.webssh.util.RestUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

/**
 * @author LiaoJL
 * @description TODO
 * @file LoginController.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 13:54
 */
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private TbUserService userService;
    @Autowired
    private TbTerminalService terminalService;

    @ApiOperation("主页转跳")
    @GetMapping("/login")
    public void logInIndex(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.sendRedirect("/index.html");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @ApiOperation("获取terminals")
    @ResponseBody
    @GetMapping("/getUserTerminals")
    public BaseResponse getTerminals(HttpServletRequest request) {

        BaseResponse baseResponse = RestUtil.getNormalResultBean();
        TbUser user = (TbUser) request.getSession().getAttribute(LoginInterceptor.user);
        if (Objects.nonNull(user)) {
            List<TbTerminal> tbTerminals = terminalService.queryByUserId(user.getId());
            if (!CollectionUtils.isEmpty(tbTerminals)) {
                baseResponse.setData(tbTerminals);
            }
        }
        return baseResponse;
    }

    @ApiOperation("用户登陆")
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse logIn(@RequestParam("name") String userName, @RequestParam("pwd") String userPwd, HttpServletRequest request) {

        BaseResponse resultBean = null;
        try {
            TbUser tbUser = new TbUser();
            tbUser.setUserName(userName);
            tbUser.setUserPass(EncryptUtil.encryptStringWithSHA1(userPwd));
            List<TbUser> tbUsers = userService.queryAllByCond(tbUser);
            if (CollectionUtils.isEmpty(tbUsers)) {
                resultBean = new ErrorResponse(ResultCode.ID_AUTHENTICATE_FAILD);
            } else {
                resultBean = RestUtil.getNormalResultBean();
                TbUser user = tbUsers.get(0);
                request.getSession().setAttribute(LoginInterceptor.user, user);
                List<TbTerminal> tbTerminals = terminalService.queryByUserId(user.getId());
                if (!CollectionUtils.isEmpty(tbTerminals)) {
                    resultBean.setData(tbTerminals);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        return resultBean;
    }
}
