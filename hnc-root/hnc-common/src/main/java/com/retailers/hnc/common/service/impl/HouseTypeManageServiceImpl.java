
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.FloorManageMapper;
import com.retailers.hnc.common.dao.FloorRelationshipMapper;
import com.retailers.hnc.common.dao.HouseTypeManageMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.vo.HouseTypeManageVo;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：户型表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:36:10
 */
@Service("housetypemanageService")
public class HouseTypeManageServiceImpl implements HouseTypeManageService {
	@Autowired
	private HouseTypeManageMapper houseTypeManageMapper;

	@Autowired
	private FloorRelationshipMapper relationshipMapper;

	@Autowired
	private FloorManageMapper manageMapper;

	public boolean saveHouseTypeManage(HouseTypeManage houseTypeManage) {
		int status = houseTypeManageMapper.saveHouseTypeManage(houseTypeManage);
		return status == 1 ? true : false;
	}
	public boolean updateHouseTypeManage(HouseTypeManage houseTypeManage) {
		HouseTypeManage manage = queryHouseTypeManageByHtId(houseTypeManage.getHtId());
		houseTypeManage.setVersion(manage.getVersion());
		int status = houseTypeManageMapper.updateHouseTypeManage(houseTypeManage);
		return status == 1 ? true : false;
	}
	public HouseTypeManage queryHouseTypeManageByHtId(Long htId) {
		return houseTypeManageMapper.queryHouseTypeManageByHtId(htId);
	}

	public Pagination<HouseTypeManageVo> queryHouseTypeManageList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<HouseTypeManageVo> page = new Pagination<HouseTypeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HouseTypeManageVo> list = houseTypeManageMapper.queryHouseTypeManageList(page);
		for (HouseTypeManageVo typeManageVo : list) {
			typeManageVo.setFloorManages(relationshipMapper.queryHouseType(typeManageVo.getHtId()));
		}
		page.setData(list);
		return page;
	}
	public boolean deleteHouseTypeManageByHtId(Long htId) {
		HouseTypeManage houseTypeManage = queryHouseTypeManageByHtId(htId);
		houseTypeManage.setIsDelete(1);
		int status = houseTypeManageMapper.updateHouseTypeManage(houseTypeManage);
		return status == 1 ? true : false;
	}

	public boolean addFloorRelationship(List<FloorRelationship> relationships){
		if(relationships != null){
			Long htId = null;
			FloorRelationship relationship = relationships.get(0);
			htId = relationship.getHtId();
			List<FloorManage> floorManages = relationshipMapper.queryHouseType(htId);
			HashMap<Long,Long> floorRelationshipMap = new HashMap<Long, Long>();
			for (FloorRelationship relations : relationships) {
				floorRelationshipMap.put(relations.getHrId(),relations.getHtId());
			}

			for (FloorManage floorManage : floorManages) {
				if(floorRelationshipMap.get(floorManage.getFmId()) == null){
					relationshipMapper.deleteFloorRelationshipByFlId(floorManage.getFmId());
				}else{
					floorRelationshipMap.remove(floorManage.getFmId());
				}
			}


		}
		return false;
	}
}

