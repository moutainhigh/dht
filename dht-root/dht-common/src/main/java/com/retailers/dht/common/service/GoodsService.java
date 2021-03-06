
package com.retailers.dht.common.service;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.vo.GoodsReturnVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 17:29:40
 */
public interface GoodsService {

	/**
	 * 添加商品表
	 * @param goods
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public Goods saveGoods(Goods goods,Long uploadpersonId);
	/**
	 * 编辑商品表
	 * @param goods
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoods(Goods goods,Long uploadpersonId);
	/**
	 * 根据id查询商品表
	 * @param gid
	 * @return goods
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public Goods queryGoodsByGid(Long gid);
	/**
	 * 查询商品表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public Pagination<GoodsVo> queryGoodsList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gid删除商品表
	 * @param gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:29:40
	 */
	public boolean deleteGoodsByGid(Long gid,Long uploadpersonId);

	public boolean checkGoods(Goods goods);

	public boolean updateGoodsSetNotChecked(Long gid,Long uploadpersonId);

	public Pagination<GoodsVo> querySamegclassGoods(Map<String, Object> params, int pageNo, int pageSize);

	/**
	 * 根据商品类型取得商品
	 * @param gt 商品类型
	 * @return
	 */
	public List<ZTreeVo> queryGoodsByGt(Long gt);

	public List<GoodsVo> queryGoodsListByGclass(Map params,int pageNo,int paeSize);

	/**
	 * 根据订单号取得商品例表
	 * @param gIds 商品ids
	 * @return
	 */
	public List<Goods> queryGoodsByIds(List<Long> gIds);

	public List<GoodsVo> queryGoodsVoByIds(Map params,int pageNo,int pageSize);

	public List<GoodsVo> queryTodayGoods(int pageNo,int pageSize);


	public List<GoodsVo> queryGoodsVoListByCondition(String condition,int pageNo,int pageSize);

	/**
	 *根据商品id 取得返现情况（该 商品是否立即返现，商品所属返现类型）
	 * @param gids
	 * @return
	 */
	public Map<Long,GoodsReturnVo> queryGoodsReturn(List<Long> gids);

	public void returnGoodsDescription();
}


