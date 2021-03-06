package com.retailers.mybatis.common.constant;

import com.alibaba.fastjson.util.TypeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数配置表 key
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/8
 */
public class SysParameterConfigConstant {
    //附件服务器地址
    public static final String ATTACHMENT_SERVER_ADDRESS="ATTACHMENT_SERVER_ADDRESS";
    /**
     * 短信验证码有效时间 分
     */
    public static final String SMS_VALID_CODE_VALID_TIME="SMS_VALID_CODE_VALID_TIME";
    /**
     * 短信验证码间隔时间 分
     */
    public static final String SMS_VALID_CODE_INTERVAL_TIME="SMS_VALID_CODE_INTERVAL_TIME";
    /**
     * 主服务器地址(pc访问）
     */
    public static final String MASTER_SERVER_PC_URL="MASTER_SERVER_PC_URL";
    /**
     * 后台管理域名 用于微信认证
     */
    public static final String MASTER_SERVER_MANAGE_URL="MASTER_SERVER_MANAGE_URL";
    /**
     * 主服务器地址（移动端）
     */
    public static final String MASTER_SERVER_MOBILE_URL="MASTER_SERVER_MOBILE_URL";

    /**
     * 用户推广提成最大值
     */
    public static final String USER_RECOMMEND_COMMISSION_MAX="USER_RECOMMEND_COMMISSION_MAX";
    //平台返现累计倍数
    public static final String PLATFORM_CASH_BACK_MULTIPLE="PLATFORM_CASH_BACK_MULTIPLE";


    /**
     * 平台默认邮费
     */
    public static final String DEFAULT_LOGISTICS_PRICE="DEFAULT_LOGISTICS_PRICE";
    /**
     * 订单失效时间
     */
    public static final String ORDER_EXPIRE_DATE="ORDER_EXPIRE_DATE";
    /**
     * 订单自动确认收货时间
     */
    public static final String ORDER_CONFIRM_DATE="ORDER_CONFIRM_DATE";
    /**
     * 订单完成时间
     */
    public static final String ORDER_COMPLETE_DATE="ORDER_COMPLETE_DATE";
    /**
     * 微信支付签名本地缓存地址
     */
    public static final String WX_PAY_CERT_LOCAL_ADDRESS="WX_PAY_CERT_LOCAL_ADDRESS";

    /**
     * 用户提现手续费
     */
    public static final String USER_CASH_MONEY_RATE="USER_CASH_MONEY_RATE";


    //系统 参数对应的key值
    public static Map<String,Object> params=new HashMap<String, Object>();

    public static String getValue(String key){
        return params.get(key).toString();
    }

    public static <T> T getValue(String key,Class<T> clazz){
        Object obj = params.get(key);
        return TypeUtils.castToJavaBean(obj, clazz);
    }

    public static void main(String[] args) {
        params.put("zpaman",true);
//        params.put("zpaman1","1");
//        params.put("zpaman2",1.2);
//        params.put("zpaman3",2.3);
        params.put("zpaman4","true");
        boolean flag = getValue("zpaman",Boolean.class);
        long num = getValue("zpaman1",Long.class);
        double d = getValue("zpaman2",Double.class);
        boolean q = getValue("zpaman4",Boolean.class);
        System.out.println(flag);
        System.out.println(num);
        System.out.println(d);
        System.out.println(q);
    }
}
