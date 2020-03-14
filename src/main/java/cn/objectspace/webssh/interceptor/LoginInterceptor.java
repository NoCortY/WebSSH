package cn.objectspace.webssh.interceptor;

import cn.objectspace.webssh.entity.TbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author LiaoJL
 * @description TODO
 * @file LoginInterceptor.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 13:22
 */
public class LoginInterceptor extends HttpFilter implements HandlerInterceptor {
    public static final String user = "USER";
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * <p>The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass
     * on the request and response to the next entity in the chain.</p>
     *
     * <p>The default implementation simply calls {@link FilterChain#doFilter}</p>
     *
     * @param req   a {@link HttpServletRequest} object that
     *              contains the request the client has made
     *              of the filter
     * @param res   a {@link HttpServletResponse} object that
     *              contains the response the filter sends
     *              to the client
     * @param chain the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an input or output error is
     *                          detected when the filter handles
     *                          the request
     * @throws ServletException if the request for the could not be handled
     * @since Servlet 4.0
     */
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }

    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined
     * an appropriate handler object, but before HandlerAdapter invokes the handler.
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can decide to abort the execution chain,
     * typically sending a HTTP error or writing a custom response.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation returns {@code true}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        boolean result = true;
        String requestURI = request.getRequestURI();
        TbUser userEntity = (TbUser) session.getAttribute(user);
        if (Objects.isNull(userEntity)) {
            log.error("{} 未通过校验 前去登录！！！", requestURI);
            result = false;
            response.sendRedirect("/login");
        }
        return result;
    }
}
