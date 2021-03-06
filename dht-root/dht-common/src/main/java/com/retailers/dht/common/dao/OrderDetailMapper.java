package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.OrderDetail;
import com.retailers.dht.common.vo.OrderDetailVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：订单详情DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-11 23:38:12
 */
public interface OrderDetailMapper {

	/**
	 * 添加订单详情
	 * @param orderDetail
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:38:12
	 */
	public int saveOrderDetail(OrderDetail orderDetail);

	/**
	 * 批量添加订单详情
	 * @param orderDetails
	 * @return
	 */
	public int saveOrderDetails(List<OrderDetail> orderDetails);
	/**
	 * 编辑订单详情
	 * @param orderDetail
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:38:12
	 */
	public int updateOrderDetail(OrderDetail orderDetail);

	/**
	 * 修改商品会员价（用钱包支付时，实际支付金额)
	 * @param ods 订单详情列表
	 * @return
	 */
	public int updateOrderDetails(List<OrderDetail> ods);
	/**
	 * 根据Id删除订单详情
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:38:12
	 */
	public int deleteOrderDetailById(Long id);
	/**
	 * 根据Id查询订单详情
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:38:12
	 */
	public OrderDetail queryOrderDetailById(Long id);

	/**
	 * 根据订单号取得订单id
	 * @param orderId
	 * @return
	 */
	public List<OrderDetail> queryOrderDetailByOdId(Long orderId);

	/**
	 * 根据订单号取得订单详情
	 * @param orderId
	 * @return
	 */
	public List<OrderDetail> queryOrderDetailByOdIds(List<Long> orderId);

	/**
	 * 查询订单详情列表
	 * @param pagination 分页对象
	 * @return  订单详情列表
	 * @author zhongp
	 * @date 2017-12-11 23:38:12
	 */
	public List<OrderDetail> queryOrderDetailList(Pagination<OrderDetail> pagination);

	/**
	 * 取得购买详情
	 * @param orderId
	 * @return
	 */
	public List<OrderDetailVo> buyOrderDetailInfos(List<Long> orderId);

	/**
	 * 根据订单id取得订单
	 * @param orderId
	 * @return
	 */
	public List<OrderDetailVo> buyOrderDetailInfosById(Long orderId);
}
