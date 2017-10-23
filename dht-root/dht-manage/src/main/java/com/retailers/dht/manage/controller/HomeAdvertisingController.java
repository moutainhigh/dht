package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.HomeAdvertising;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.HomeAdvertisingService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/18.
 */
@Controller
@RequestMapping("openHomeAdvertising")
public class HomeAdvertisingController extends BaseController{
    @Autowired
    HomeAdvertisingService advertisingService;

    @Autowired
    AttachmentService attachmentService;

    @RequestMapping("/homeAdvertisingMapping")
    @Menu(parentRes = "sys.manager.floorManage",resourse = "openHomeAdvertising.homeAdvertisingMapping",description = "首页广告设置",label = "首页广告设置")
    public String homeAdvertisingMapping(){
        return "navigation/homeAdvertising";
    }

    @RequestMapping("/addAdvertising")
    @Function(label = "添加首页广告",description = "添加首页广告",resourse = "openHomeAdvertising.addNavigatorBar",sort = 3,parentRes = "openHomeAdvertising.homeAdvertisingMapping")
    @ResponseBody
    public BaseResp addNavigatorBar(HomeAdvertising advertising){
        attachmentService.editorAttachment(advertising.getImagePath());
        boolean flag = advertisingService.saveHomeAdvertising(advertising);
        if(flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    @RequestMapping("/updateAdvertising")
    @Function(label = "修改首页广告",description = "修改首页广告",resourse = "openHomeAdvertising.updateNavigatorBar",sort = 3,parentRes = "openHomeAdvertising.homeAdvertisingMapping")
    @ResponseBody
    public BaseResp updateNavigatorBar(HomeAdvertising advertising){
        HomeAdvertising homeAdvertising = advertisingService.queryHomeAdvertisingByHaId(advertising.getHaId());
        if(homeAdvertising.getImagePath().compareTo(advertising.getImagePath()) != 0) {
            attachmentService.editorAttachment(homeAdvertising.getImagePath(), AttachmentConstant.ATTACHMENT_STATUS_NO);
            attachmentService.editorAttachment(advertising.getImagePath());
        }
        advertising.setVersion(homeAdvertising.getVersion());
        boolean flag = advertisingService.updateHomeAdvertising(advertising);
        if(flag)
            return success("修改首页导航[" + advertising.getHaName() + "]成功");
        else
            return errorForSystem("修改首页导航[" + advertising.getHaName() + "]失败");
    }

    @RequestMapping("/removeAdvertising")
    @Function(label = "删除首页广告",description = "删除首页广告",resourse = "openHomeAdvertising.removeNavigatorBar",sort = 3,parentRes = "openHomeAdvertising.homeAdvertisingMapping")
    @ResponseBody
    public BaseResp removeAdvertising(Long haId){
        boolean flag = advertisingService.deleteHomeAdvertisingByHaId(haId);
        return  success(flag);
    }

    @RequestMapping("/queryAdvertisingLists")
    @Function(label="首页广告集合", description = "首页广告集合", resourse = "openHomeAdvertising.queryNavigationLists",sort=1,parentRes="openHomeAdvertising.homeAdvertisingMapping")
    @ResponseBody
    public Map<String,Object> queryNavigationLists(String gtName, Long isShow, PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gtName",gtName);
        map.put("isShow",isShow);
        Pagination<HomeAdvertising> advertisingPagination = advertisingService.queryHomeAdvertisingList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getTotalCount());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }
}
