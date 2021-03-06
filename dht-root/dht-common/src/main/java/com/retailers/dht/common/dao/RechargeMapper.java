package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.dht.common.view.RechargeView;
import com.retailers.dht.common.vo.RechargeVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：充值管理DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 23:56:18
 */
public interface RechargeMapper {

	/**
	 * 添加充值管理
	 * @param recharge
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public int saveRecharge(Recharge recharge);

	/**
	 * 添加快照
	 * @param recharge
	 * @return
	 */
	public int saveRechargeSnapshot(Recharge recharge);
	/**
	 * 编辑充值管理
	 * @param recharge
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public int updateRecharge(Recharge recharge);
	/**
	 * 根据Rid删除充值管理
	 * @param rid
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public int deleteRechargeByRid(Long rid);
	/**
	 * 根据Rid查询充值管理
	 * @param rid
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public Recharge queryRechargeByRid(Long rid);

	/**
	 * 取得用户购买的充值卡
	 * @param rid
	 * @return
	 */
	public Recharge queryUserBuyRecharge(Long rid);
	/**
	 * 查询充值管理列表
	 * @param pagination 分页对象
	 * @return  充值管理列表
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public List<RechargeVo> queryRechargeList(Pagination<RechargeVo> pagination);

	/**
	 * 取得充值卡列表 用户
	 * @return
	 */
	public List<RechargeView> queryRechargeLists();

}
