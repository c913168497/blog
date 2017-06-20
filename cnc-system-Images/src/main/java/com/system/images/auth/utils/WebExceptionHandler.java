package com.system.images.auth.utils;

import com.cnc.common.lang.utils.JSONUtil;
import com.cnc.common.web.util.MessageBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @描述: web层的全局异常处理
 * @作者: haw
 * @创建时间: 2017-02-23
 * @版本: 1.0
 */
public class WebExceptionHandler implements HandlerExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("捕获异常："+e.getMessage(),e);
        if(isAjaxRequest(httpServletRequest)){
            try {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
                httpServletResponse.setStatus(500);
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(JSONUtil.object2Json(MessageBox.getErrorMsg("服务器异常！")));
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        return new ModelAndView("error");
    }


    protected boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(header) ? true:false;
        return isAjax;
    }
}
