package com.retailers.dht.web.controller;

import com.retailers.auth.entity.SysUser;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/9
 */
@Controller
public class HomeController extends BaseController {
    /**
     * 主页访问地址
     * @param request
     * @return
     */
    @RequestMapping(value = {"","/","/index"})
    public String home(HttpServletRequest request){
        UserInfoVIew userInfo=new UserInfoVIew();
        userInfo.setUid(-1l);;
        setCurLoginUser(request,userInfo);
        return redirectUrl(request,"index");
    }


    @RequestMapping("/home/{id}.html")
    public String randomHome(HttpServletRequest request, @PathVariable("id")String id){
        return redirectUrl(request,"index");
    }
}
