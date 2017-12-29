package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.vo.EmRelationshipVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：分配预约名额关系表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 13:48:07
 */
public interface EmRelationshipMapper {

	/**
	 * 添加分配预约名额关系表
	 * @param emRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public int saveEmRelationship(EmRelationship emRelationship);
	/**
	 * 编辑分配预约名额关系表
	 * @param emRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public int updateEmRelationship(EmRelationship emRelationship);
	/**
	 * 根据ErId删除分配预约名额关系表
	 * @param erId
	 * @return
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public int deleteEmRelationshipByErId(Long erId);
	/**
	 * 根据ErId查询分配预约名额关系表
	 * @param erId
	 * @return
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public EmRelationship queryEmRelationshipByErId(Long erId);
	/**
	 * 查询分配预约名额关系表列表
	 * @param pagination 分页对象
	 * @return  分配预约名额关系表列表
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public List<EmRelationship> queryEmRelationshipList(Pagination<EmRelationship> pagination);

	/**
	 * 查询开盘期数所对置业顾问分配的名额
	 * @return  分配预约名额关系表列表
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public List<EmRelationshipVo> queryOpeningEmployees(Long pId);

	/**
	 * 查询开盘期数所
	 * @return  分配预约名额关系表列表
	 * @author wangjue
	 * @date 2017-12-29 13:48:07
	 */
	public List<EmRelationship> queryOpeningTeam(Long pId);

}
