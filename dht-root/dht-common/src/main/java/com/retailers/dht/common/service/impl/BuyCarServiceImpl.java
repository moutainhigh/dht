
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.dao.BuyCarMapper;
import com.retailers.dht.common.service.BuyCarService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：购物车表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 15:01:54
 */
@Service("buycarService")
public class BuyCarServiceImpl implements BuyCarService {
	@Autowired
	private BuyCarMapper buyCarMapper;
	public boolean saveBuyCar(BuyCar buyCar) {
		int status = buyCarMapper.saveBuyCar(buyCar);
		return status == 1 ? true : false;
	}
	public boolean updateBuyCar(BuyCar buyCar) {
		int status = buyCarMapper.updateBuyCar(buyCar);
		return status == 1 ? true : false;
	}
	public BuyCar queryBuyCarByBcId(Long bcId) {
		return buyCarMapper.queryBuyCarByBcId(bcId);
	}

	public Pagination<BuyCar> queryBuyCarList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<BuyCar> page = new Pagination<BuyCar>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<BuyCar> list = buyCarMapper.queryBuyCarList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteBuyCarByBcId(Long bcId) {
		BuyCar buyCar = buyCarMapper.queryBuyCarByBcId(bcId);
		buyCar.setIsDelete(1L);
		int status = buyCarMapper.updateBuyCar(buyCar);
		return status == 1 ? true : false;
	}
}
