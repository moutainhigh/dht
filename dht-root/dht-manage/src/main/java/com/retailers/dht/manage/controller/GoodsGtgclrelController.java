package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsGtgclrelService;
import com.retailers.dht.common.service.GoodsTypeService;
import com.retailers.dht.common.vo.GoodsGtgclrelVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsGtgclrelController extends BaseController {

    @Autowired
    GoodsGtgclrelService goodsGtgclrelService;
    @Autowired
    GoodsClassificationService goodsClassificationService;

    @RequestMapping("/removeGoodsGtgclrel")
    @ResponseBody
    public BaseResp removeGoodsGtgclrel(String gtgclIds){
        boolean flag=goodsGtgclrelService.deleteGoodsGtgclrelByGtgclId(gtgclIds);
        return success(flag);
    }

    @RequestMapping("/queryGoodsGtgclrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGtgclrelLists(Long gtId,String gclName,Long ggId,Long isClass,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(ggId)){
            map.put("gtId",goodsClassificationService.queryGoodsClassificationByGgId(ggId).getGgHome());
        }else{
            map.put("gtId",gtId);
        }
        map.put("gclName",gclName);
        map.put("isClass",isClass);
        map.put("isDelete",0);
        List<GoodsGtgclrelVo> list = goodsGtgclrelService.queryGoodsGtgclrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/addGoodsGtgclrel")
    @ResponseBody
    public BaseResp addGoodsGtgclrel(String gclIds,Long gtId){
        boolean flag=false;
        if(ObjectUtils.isNotEmpty(gclIds)){
            flag = goodsGtgclrelService.saveGoodsGtgclrel(gclIds,gtId);
        }

        return success(flag);
    }

}
