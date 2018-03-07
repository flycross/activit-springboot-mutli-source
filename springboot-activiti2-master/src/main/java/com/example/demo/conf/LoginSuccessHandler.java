package com.example.demo.conf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.activiti.util.UserUtil;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * <p/>
 * 登录成功后选择要跳转的默认页.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

    private IdentityService identityService;


    /**
     * redirectStrategy
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException
    {
        HttpServletRequest obj = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       /* SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        securityUser.setJsessionId(obj.getSession().getId());*/
        super.onAuthenticationSuccess(request, response, authentication);
    }

    /**
     * 获取ip地址
     * @param request  
     * @return String
     */
    public String getIpAddress(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * handle
     * @see org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler
     * #handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        authentication.getName();



        Map<String, Object> map = (Map<String, Object>) authentication.getPrincipal();
        User user = identityService.createUserQuery().userId(map.get("username").toString()).singleResult();
        UserUtil.saveUserToSession(request.getSession(), user);

        String targetUrl = "/main/index";


        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    public boolean isAjax(HttpServletRequest request)
    {
        return (request.getHeader("X-Requested-With") != null
            && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

}
