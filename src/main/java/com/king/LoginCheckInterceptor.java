package com.king;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by king on 2017/5/11.
 */
public class LoginCheckInterceptor implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(LoginCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("[loginCheck]pre...");
        request.setAttribute("userId", 1);

        request.getParameterMap().entrySet().forEach(entry->log.debug("[paramCheck]" + entry.getKey() + "->" + Arrays.toString(entry.getValue())));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.addHeader( "Access-Control-Allow-Origin", "*" ); // open your api to any client
        response.addHeader( "Access-Control-Allow-Methods", "POST" ); // a allow post
        response.addHeader( "Access-Control-Max-Age", "1000" ); // time from request to response before timeout
    }
}
