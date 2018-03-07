package com.example.demo.aspect;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.activiti.util.UserUtil;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



/**
 * Web cas 登陆Session灯状态切面拦截
 *
 * @version 1.0.0
 */
@Aspect
@Order(5)
@Component
public class WebLoginAspect {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(getClass());

    private IdentityService identityService;


    @Value("${url.cas.server}")
    private String cassever;

    /**
     * 开始时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 只是一个标记，方法体不会被执行，类似于spring2.x配置文件里面的<aop:pointcut id="beforePointCut" ...>
     * 具体逻辑在befor after,around里写
     */
    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void casLogin() {
    }

    /**
     * @param joinPoint @see org.aspectj.lang.JoinPoint
     * @throws Throwable
     */
    @Before("casLogin()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        request.setAttribute("cassever", cassever);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return;
        }


        try {
            request.setAttribute("cassever", cassever);
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (map != null) {
                // 传入账号信息
                String userName = map.get("username").toString();
                Object password = map.get("password");
                request.setAttribute("account", userName);
                request.setAttribute("password", password);
                HttpSession session=request.getSession();
                User user = identityService.createUserQuery().userId(userName).singleResult();
                UserUtil.saveUserToSession(session, user);
                List<Group> groupList = identityService.createGroupQuery().groupMember(userName).list();
                session.setAttribute("groups", groupList);
                String[] groupNames = new String[groupList.size()];
                for (int i = 0; i < groupNames.length; i++) {
                    System.out.println(groupList.get(i).getName());
                    groupNames[i] = groupList.get(i).getName();
                }
                session.setAttribute("groupNames", ArrayUtils.toString(groupNames));

            }
        } catch (Exception e) {
            logger.info("no login");
        }

    }

    /**
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "casLogin()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容

    }


    @Autowired
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

}
