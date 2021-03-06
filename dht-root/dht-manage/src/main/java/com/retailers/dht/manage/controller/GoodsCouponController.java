package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品优惠活动
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/9
 */
@Controller
@RequestMapping("goodsCoupon")
public class GoodsCouponController extends BaseController{
    @Autowired
    private GoodsCouponService goodsCouponService;

    /**
     * 打开商品优惠管理
     * @return
     */
    @RequestMapping("openGoodsCouponPage")
    @Menu(resourse = "goodsCoupon.openGoodsCouponPage",parentRes = "sys.manager.promotion",label = "商品优惠",sort = 4)
    public String openGoodsCouponPage(){
        return "promotion/goods_coupon";
    }

    /**
     * 取得商品优惠
     * @param gcpName 优惠名称
     * @param gcpType 类型
     * @param isValid 是否有效
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryGoodsCoupons")
    @Function(label="商品优惠列表", description = "商品优惠列表", resourse = "goodsCoupon.queryGoodsCoupons",sort=1,parentRes="goodsCoupon.openGoodsCouponPage")
    @ResponseBody
    public Map<String,Object> queryGoodsCoupons(String gcpName, Long gcpType,Long isValid,String now,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("gcpName",gcpName);
        params.put("gcpType",gcpType);
        params.put("isValid",isValid);
        params.put("isDelete",0);
        params.put("type", CouponUseRangeConstant.TYPE_GOODS_COUPON);
        System.out.println(now);
        if(!ObjectUtils.isEmpty(now)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date nowDate = sdf.parse(now);
                System.out.println(nowDate);
                params.put("now",nowDate);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Pagination<GoodsCouponShowVo> pages= goodsCouponService.queryGoodsCouponList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 添加商品优惠
     * @param goodsCoupon 商品优惠数据
     * @return
     */
    @RequestMapping("addGoodsCoupon")
    @Function(label="添加商品优惠", description = "添加商品优惠", resourse = "goodsCoupon.addGoodsCoupon",sort=2,parentRes="goodsCoupon.openGoodsCouponPage")
    @ResponseBody
    public BaseResp addGoodsCoupon(GoodsCouponVo goodsCoupon){
        try{
            validateParams(goodsCoupon);
        }catch(Exception e){
            return errorForParam(e.getMessage());
        }
        boolean flag = goodsCouponService.saveGoodsCoupon(goodsCoupon);
        if(flag){
            return success("商品优惠添加成功");
        }else{
            return success("商品优惠添加失败");
        }
    }
    /**
     * 添加商品优惠
     * @param goodsCouponVo 商品优惠数据
     * @return
     */
    @RequestMapping("editorGoodsCoupon")
    @Function(label="编辑商品优惠", description = "编辑商品优惠", resourse = "goodsCoupon.editorGoodsCoupon",sort=3,parentRes="goodsCoupon.openGoodsCouponPage")
    @ResponseBody
    public BaseResp editorGoodsCoupon(GoodsCouponVo goodsCouponVo){
        try{
            validateParams(goodsCouponVo);
        }catch(Exception e){
            return errorForParam(e.getMessage());
        }
        boolean flag = goodsCouponService.editorGoodsCoupon(goodsCouponVo);
        if(flag){
            return success("商品优惠编辑成功");
        }else{
            return success("商品优惠编辑失败");
        }
    }

    /**
     * 校验传入参数
     * @param goodsCoupon
     * @throws AppException
     */
    private void validateParams(GoodsCouponVo goodsCoupon)throws AppException{
        validateForm(goodsCoupon);
        if(goodsCoupon.getGcpType().intValue()== CouponConstant.GCP_TYPE_MONEY){
            if(ObjectUtils.isEmpty(goodsCoupon.getGcpMoney())){
                throw new AppException("优惠活动为代金卷时优惠金额不能为空");
            }
            goodsCoupon.setGcpDiscount(null);
        }
        if(goodsCoupon.getGcpType().intValue()== CouponConstant.GCP_TYPE_DISCOUNT){
            if(ObjectUtils.isEmpty(goodsCoupon.getGcpDiscount())){
                throw new AppException("优惠活动为折扣卷时折扣额不能为空");
            }
            goodsCoupon.setGcpMoney(null);
        }
        //优惠受限 指定商品种类或指定商品不能全部为空
        if(goodsCoupon.getGcpIsRestricted().intValue()!=CouponConstant.COUPON_USED_RANGE_ALL&&ObjectUtils.isEmpty(goodsCoupon.getSpId())&&ObjectUtils.isEmpty(goodsCoupon.getSpzlId())){
            throw new AppException("使用范围受限时，指定商品种类或指定商品不能全部为空");
        }
        //包邮时清除金额以及折扣卷值
        if(goodsCoupon.getGcpType().intValue()== CouponConstant.GCP_TYPE_FREE_SHIPPING){
            goodsCoupon.setGcpDiscount(null);
            goodsCoupon.setGcpMoney(null);
        }
    }

    /**
     * 删除商品优惠
     * @param gcpId
     * @return
     */
    @RequestMapping("delGoodsCoupon")
    @Function(label="删除商品优惠", description = "删除商品优惠", resourse = "goodsCoupon.delGoodsCoupon",sort=2,parentRes="goodsCoupon.openGoodsCouponPage")
    @ResponseBody
    public BaseResp delGoodsCoupon(Long gcpId){
        if(ObjectUtils.isEmpty(gcpId)){
            return errorForParam("删除商品优惠id不能为空");
        }
        boolean flag = goodsCouponService.deleteGoodsCouponByGcpId(gcpId);
        return success(flag);
    }

    /**
     * 根据商品id取得所有的商品优惠
     * @param goodsId
     * @return
     */
    @RequestMapping("queryGoodsCouponByGid")
    @ResponseBody
    public BaseResp queryGoodsCouponByGid(Long goodsId){
        List<GoodsCouponShowVo> list = goodsCouponService.queryGoodsCouponByGid(goodsId);
        return success(list);
    }
    /**
     *根据商品id 和 优惠名称 取得所有的优惠列表（排除该 商品己存在的）
     * @param goodsId
     * @param couponNm
     * @return
     */
    @RequestMapping("queryUnBindGoodsCouponByGid")
    @ResponseBody
    public BaseResp queryUnBindGoodsCouponByGid(String couponNm,Long goodsId){
        List<GoodsCouponShowVo> list = goodsCouponService.queryUnBindGoodsCouponByGid(couponNm,goodsId);
        return success(list);
    }

    /**
     * 商品绑了定优惠
     * @param goodsId 商品id
     * @param gcpIds 优惠ids（多个之间用逗号隔开)
     * @return
     */
    @RequestMapping("goodsBindCoupon")
    @ResponseBody
    public BaseResp goodsBindCoupon(Long goodsId,String gcpIds){
        if(ObjectUtils.isEmpty(goodsId)){
            return errorForParam("商品id不能为空");
        }
        if(ObjectUtils.isEmpty(gcpIds)){
            return errorForParam("优惠id不能为空");
        }
        try{
            boolean flag = goodsCouponService.goodsBindCoupon(goodsId,gcpIds);
            return success(flag);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }
    }
    /**
     * 商品绑了定优惠
     * @param goodsId 商品id
     * @param gcpIds 优惠ids（多个之间用逗号隔开)
     * @return
     */
    @RequestMapping("goodsUnBindCoupon")
    @ResponseBody
    public BaseResp goodsUnBindCoupon(Long goodsId,String gcpIds){
        if(ObjectUtils.isEmpty(goodsId)){
            return errorForParam("商品id不能为空");
        }
        if(ObjectUtils.isEmpty(gcpIds)){
            return errorForParam("优惠id不能为空");
        }
        try{
            boolean flag = goodsCouponService.goodsUnBindCoupon(goodsId,gcpIds);
            return success(flag);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }
    }
}
