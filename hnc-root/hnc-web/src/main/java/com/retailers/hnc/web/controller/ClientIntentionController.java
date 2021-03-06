package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.service.ClientIntentionService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/27.
 */
@Controller
@RequestMapping("clientIntention")
public class ClientIntentionController extends BaseController{
    @Autowired
    ClientIntentionService clientIntentionService;

    @RequestMapping("/queryClientIntentionList")
    @ResponseBody
    public Map<String,Object> queryClientIntentionList(String randStr){
        Long cid = getClientIdByOpenId(randStr);
        System.out.println(cid);
        Map<String,Object> map = new HashMap<String,Object>();
        List<ClientIntentionVo> list = clientIntentionService.queryClientIntentionVoListByCmId(cid);
        if(ObjectUtils.isNotEmpty(list)){
            map.put("rows",list);
        }
        return map;
    }

    @RequestMapping("/queryAllHouseType")
    @ResponseBody
    public Map<String,Object> queryAllHouseType(String fmIds){
        Map<String,Object> map = new HashMap<String,Object>();
        if(ObjectUtils.isNotEmpty(fmIds))
            map.put("houseTypeList", clientIntentionService.queryAllHouseType(fmIds));
        return map;
    }

    @RequestMapping("/queryClientIntentionListByCmId")
    @ResponseBody
    public Map<String,Object> queryClientIntentionListByCmId(Long cmId){
        Long cid = cmId;
        Map<String,Object> map = new HashMap<String,Object>();
        List<ClientIntentionVo> list = clientIntentionService.queryClientIntentionVoListByCmId(cid);
        if(ObjectUtils.isNotEmpty(list)){
            map.put("rows",list);
        }
        return map;
    }

    @RequestMapping("/deleteClientIntention")
    @ResponseBody
    public BaseResp deleteClientIntention(Long iid){
        boolean flag = clientIntentionService.deleteClientIntentionByIid(iid);
        return success(flag);
    }

    @RequestMapping("/saveClientIntention")
    @ResponseBody
    public BaseResp saveClientIntention(ClientIntention clientIntention,String randStr){
        Long cmId = getClientIdByOpenId(randStr);
        clientIntention.setCmId(cmId);
        clientIntention.setIsDelete(0L);
        boolean flag = clientIntentionService.saveClientIntention(clientIntention);
        return success(flag);
    }

    @RequestMapping("/updateClientIntention")
    @ResponseBody
    public BaseResp updateClientIntention(ClientIntention clientIntention,String randStr){
        Long cmId = getClientIdByOpenId(randStr);
        clientIntention.setCmId(cmId);
        clientIntention.setIsDelete(0L);
        boolean flag = clientIntentionService.updateClientIntention(clientIntention);
        return success(flag);
    }
}
