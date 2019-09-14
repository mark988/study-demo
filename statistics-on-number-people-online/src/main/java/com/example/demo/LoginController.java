package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * LoginController class
 * @author zcz
 * @date 2018/09/20
 */
@RequestMapping("/test")
@RestController
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录
     */
    @GetMapping("/Login")
    public void getUserByUserNameAndPassword(@RequestParam String userName, HttpSession  session) {
         session.setAttribute("loginName",userName);
         return;
    }
    /**
     *查询在线人数
     */
    @RequestMapping("/online")
    public Object online() {
        return  "当前在线人数：" + MyHttpSessionListener.online + "人";
    }
    /**
     * 退出登录
     */
    @RequestMapping("/Logout")
    public String Logout( HttpServletRequest request) {
        logger.info("用户退出登录开始！");
        HttpSession session = request.getSession(false);//防止创建Session
        if(session != null){
            session.removeAttribute("loginName");
            session.invalidate();
        }
        logger.info("用户退出登录结束！");
        return  "退出成功";
    }


    /**
     * 判断session是否有效
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String loginName = (String) session.getAttribute("loginName");
        if (loginName!=null) {
            return "200";
        }
        return "";
    }

}
