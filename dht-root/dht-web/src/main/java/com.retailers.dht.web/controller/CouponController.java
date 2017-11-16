package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/15
 */
@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController{
    @Autowired
    private CouponService couponService;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("openCoupon")
    public String openCoupon(HttpServletRequest request){
        return redirectUrl(request,"coupon/coupon");
    }

    /**
     * 优惠卷列表
     * @param request
     * @return
     */
    @RequestMapping("couponList")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = "未登录，请登录后再领取")
    @ResponseBody
    public BaseResp couponList(HttpServletRequest request,PageUtils pageForm){
        Long uid=getCurLoginUserId(request);
        try{
           List<Coupon> lists= couponService.queryCouponList(uid,pageForm.getPageNo(),pageForm.getPageSize());
            return success(lists);
        }catch(AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 取得用户的优惠卷
     * @param request
     * @return
     */
    @RequestMapping("queryUserCoupon")
    @ResponseBody
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = "未登录，请登录")
    public BaseResp queryUserCoupon(HttpServletRequest request){
        return success(null);
    }
}
