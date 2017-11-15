
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsInventoryVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import java.util.Map;
/**
 * 描述：商品与规格值详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 09:52:11
 */
public interface GoodsGgsvalDetailService {

	/**
	 * 添加商品与规格值详情表
	 * @param goodsGgsvalDetail
	 * @return
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public boolean saveGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail);
	/**
	 * 编辑商品与规格值详情表
	 * @param goodsGgsvalDetail
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail);
	/**
	 * 根据id查询商品与规格值详情表
	 * @param ggdId
	 * @return goodsGgsvalDetail
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public GoodsGgsvalDetail queryGoodsGgsvalDetailByGgdId(Long ggdId);
	/**
	 * 查询商品与规格值详情表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public Pagination<GoodsGgsvalDetail> queryGoodsGgsvalDetailList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggdId删除商品与规格值详情表
	 * @param ggdId
	 * @return
	 * @author fanghui
	 * @date 2017-10-20 09:52:11
	 */
	public boolean deleteGoodsGgsvalDetailByGgdId(Long ggdId);

	public boolean clearAllGgsrel(Long gid,Long updatepersonId);

	public Pagination<GoodsInventoryVo> queryGoodsInventoryLists(Map<String, Object> params, int pageNo, int pageSize);

	public boolean editGoodsInventory(Long gdId,Long inventory);
}

