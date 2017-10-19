
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGgsval;
import java.util.Map;
/**
 * 描述：商品与规格值关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 18:43:18
 */
public interface GoodsGgsvalService {

	/**
	 * 添加商品与规格值关系表
	 * @param goodsGgsval
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public boolean saveGoodsGgsval(GoodsGgsval goodsGgsval);
	/**
	 * 编辑商品与规格值关系表
	 * @param goodsGgsval
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGgsval(GoodsGgsval goodsGgsval);
	/**
	 * 根据id查询商品与规格值关系表
	 * @param ggsId
	 * @return goodsGgsval
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public GoodsGgsval queryGoodsGgsvalByGgsId(Long ggsId);
	/**
	 * 查询商品与规格值关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public Pagination<GoodsGgsval> queryGoodsGgsvalList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggsId删除商品与规格值关系表
	 * @param ggsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public boolean deleteGoodsGgsvalByGgsId(Long ggsId);

}


