package com.bar.common.aop.aspect;


import com.alibaba.fastjson.JSON;
import com.bar.common.bean.ResultBean;
import com.bar.common.custom.constant.ServiceCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.BindException;

/**
 * 日志检测类
 */
@Aspect
@Component
@SuppressWarnings("all")
public class ServiceAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com..service..*.*(..))")
    public Object doServiceAround(ProceedingJoinPoint call) throws Throwable {
        log.info("");
        String methodName = call.getSignature().getName();
        Thread.currentThread().setName(methodName + "-" + System.currentTimeMillis());
        String classType = call.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        log.info("clazzName: "+clazzName);
        /* 输入参数打印逻辑处理 */
        String params = "无法转成JSON字符串";
        try {
            params = JSON.toJSONString(call.getArgs());
        } catch (Exception e) {
            for (Object obj : call.getArgs()) {
                if (obj instanceof BindException) {
                    params = "参数验证不通过";
                    break;
                }
            }
        }
        if (params.length() > 200) params = params.substring(0, 200) + "...";
        log.info("-----［Ｓｅｒｖｉｃｅ　Ｍｅｔｈｏｄ］：" + methodName + " ---　［Ｓｔａｒｔ］　 Params：" + params);

        /* 接口主逻辑 */
        Long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = call.proceed();
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.info(e.getMessage());
            result = new ResultBean<>(ServiceCode.ERROR, ServiceCode.ERROR_DEFAULT_MSG, null);
        }
        Long endTime = System.currentTimeMillis();

        /* 接口返回值打印处理 */
        String resultStr = "无法转成JSON字符串";
        try {
            resultStr = JSON.toJSONString(result);
            if (resultStr != null && resultStr.length() > 200) {
                resultStr = resultStr.substring(0, 200) + "...";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("-----［Ｓｅｒｖｉｃｅ　Ｍｅｔｈｏｄ］：" + methodName + " ------　［Ｅｎｄ］　 Result：[" + resultStr + "]");
        log.info("---Runtime：" + (endTime - startTime) + "ms");
        log.info("");
        return result;
    }
}
