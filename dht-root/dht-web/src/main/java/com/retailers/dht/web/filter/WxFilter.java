package com.retailers.dht.web.filter;


import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.tools.base.WriteData;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.CheckMobile;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 微信过滤器(微信端 访问时取得用户openid）
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/25
 */
public class WxFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(WxFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 过滤器
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        logger.info("进入ManagerFilter 的doFilter 方法");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //取得访问端口类型
        String userAgent= request.getHeader( "USER-AGENT" );
        if(ObjectUtils.isNotEmpty(userAgent)){
            userAgent = userAgent.toLowerCase();
        }else{
            userAgent="";
        }
        boolean isFromMobile= CheckMobile.check(userAgent);
        String uri_ = request.getRequestURI();
        logger.info("请求url：[{}]",uri_);
        //判断是否存在推荐人
        String randStr=request.getParameter("randStr");
        if(ObjectUtils.isNotEmpty(randStr)){
            String uri = request.getRequestURI();
            cachInviter(request,randStr,false);
        }
        //判断是否是重新加载数据方法
        if(uri_.indexOf("reaload")>=0){
            Map<String,Object> parms = WebUtils.getParametersStartingWith(request,"");
            if (!SignUtil.encryptDES(parms)) {
                WriteData.paramError("非法请求!",response);
                return;
            }else{
                chain.doFilter(request, response);
                return;
            }
        }
        String uri = request.getRequestURI();
        if(uri.indexOf(".")>=0&&uri.indexOf(".html")<0){
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        //判断是否为移动端访问 移动端访问
        if(isFromMobile){
            //判断是否是微信
            if(userAgent.indexOf("micromessenger")>0){
                //判断是否己授权登录
                Object obj =  request.getSession().getAttribute(SystemConstant.IS_PULL_WX_USER_INFO);
                //未登录 页面重定向 获取用户openid
                if(ObjectUtils.isEmpty(obj)&&!uri.endsWith("wx/userLogin")&&!uri.endsWith("/loginPage")){
                    //跳转至公众号授权注册页面
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/wx/toAuth?"+
                            com.retailers.dht.common.constant.SystemConstant.WX_ACCESS_ADDRESS_AUTH_URL+"="+
                            URLEncoder.encode(urlParams(request), com.retailers.dht.common.constant.SystemConstant.DEFAUT_CHARSET));
                    dispatcher.forward(request, response);
                    return;
                }
                //判断是否绑定用户
            }
        }else{
            //判断是否是微信支付回调
            if(uri.indexOf("wxPay/callback")>=0){
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
            //跳转至公众号授权注册页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pcPage");
            dispatcher.forward(request, response);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
        return;
    }
    public void destroy() {

    }

    /**
     * 取得访问的url参数并组成字符
     * @param request
     * @return
     */
    private String urlParams(HttpServletRequest request){
        Map<String,Object> params=WebUtils.getParametersStartingWith(request,"");
        String uri = request.getRequestURI();
        StringBuffer sb = new StringBuffer(uri);
        if(ObjectUtils.isNotEmpty(params)){
            int i=0;
            for(String key : params.keySet()) {
                i++;
                if(i==1){
                    sb.append("?" + key + "=" + params.get(key));
                }else{
                    sb.append("&" + key + "=" + params.get(key));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 缓存推荐人
     * @param request
     * @param randStr
     */
    private void cachInviter(HttpServletRequest request,String randStr,boolean decode){
        logger.info("取得推荐人信息:[{}]",randStr);
        try{
            String randDecode= randStr;
            if(decode){
                randDecode = URLDecoder.decode(randStr, com.retailers.dht.common.constant.SystemConstant.DEFAUT_CHARSET);
            }
            logger.info("传入参数decode 之后的结果:[{}]",randDecode);
            //解密推荐人信息
            String decryptInfo= DESUtils.decryptDES(randDecode, DesKey.WEB_KEY);
            if(decryptInfo.indexOf("_")>=0){
                String uid=decryptInfo.split("_")[0];
                if(ObjectUtils.isNotEmpty(uid)){
                    logger.info("取得推荐人用户ID:[{}]",uid);
                    request.getSession().setAttribute(SystemConstant.SHARE_USER_SESSION_KEY,Long.parseLong(uid));
                }
            }
        }catch(IllegalArgumentException e){
            if(!decode){
                cachInviter(request,randStr,true);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("解密推荐人异常，推荐人信息：[{}],异常信息:\r\n{}",randStr,e);
        }
    }
}
