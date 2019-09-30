package com.ezhar.handler;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
//全局异常处理类
/*拦截所有以@controller标记的类*/
@ControllerAdvice
public class ControllerExceptionHandler {

     private final Logger logger= LoggerFactory.getLogger(this.getClass());
    /*表示该方法是异常处理的,获取所有Exception级别的异常*/
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //记录异常信息的路径和异常信息
        logger.error("Request URL :{},Exception :{}",request.getRequestURL(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        //把记录到的信息放入到对象中
        ModelAndView mv=new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        //指定返回的页面
        mv.setViewName("error/error");
        return mv;
    }
}