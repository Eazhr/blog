package com.ezhar.aspect;
/*记录操作日志类*/
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/*定义当前为切面类*/
@Aspect
@Component
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    /*拦截controller里面的所有类所有方法*/
    @Pointcut("execution(* com.ezhar.controller.*.*(..))")
    public void log(){

    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        //获取参数
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        //用切面类的joinPoint对象来获取controller类里面的类名，方法名
        String classMethod =joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        //构造对象，传入获取到的参数
        RequestLog requestLog= new RequestLog(url,ip,classMethod,args);
        logger.info("request : {} ",requestLog);
    }
    @After("log()")
    public void doAfter(){

    }
    /*切入点log，返回值result*/
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("result :{}" ,result);
    }
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
