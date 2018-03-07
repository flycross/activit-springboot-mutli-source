package com.example.demo.activiti.util;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户工具类
 *
 * @author HenryYan
 */
public class UserUtil {

    public static final String USER = "user";

    /**
     * 设置用户到session
     *
     * @param session
     * @param user
     */
    public static void saveUserToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }

    /**
     * 从Session获取当前用户信息
     *
     * @param session
     * @return
     */
    public static User getUserFromSession(HttpSession session) {

        Object attribute = session.getAttribute(USER);
        return attribute == null ? null : (User) attribute;

       // Object attribute = session.getAttribute(USER);

        //Object attribute =null;

       /* User user=null;
        if( SecurityContextHolder.getContext()!=null)
        {
            Map<String, Object> map = (Map<String, Object>)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Map<String, Object> userObj = (Map<String, Object>) map.get("user");
              user = GetFormSecurtiy(userObj);
        }
        return user == null ? null : user;*/
    }

    private static   User GetFormSecurtiy(  Map<String, Object> map)
    {
        UserEntity user=new UserEntity();
        user.setEmail(map.get("account").toString());
        user.setId(map.get("account").toString());
        user.setPassword(map.get("password").toString());
        return  user;

    }

}
