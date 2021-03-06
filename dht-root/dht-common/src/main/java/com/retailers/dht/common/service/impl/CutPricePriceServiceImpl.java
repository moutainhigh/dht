
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.CutPricePriceMapper;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.service.CutPricePriceService;
import com.retailers.dht.common.service.CutPriceService;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsGdcprelService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.ga.LocaleNames_ga;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 描述：砍价价格初始化表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-07 15:52:00
 */
@Service("cutpricepriceService")
public class CutPricePriceServiceImpl implements CutPricePriceService {
	@Autowired
	private CutPricePriceMapper cutPricePriceMapper;
	@Autowired
	private GoodsGdcprelService goodsGdcprelService;
	@Autowired
	private CutPriceService cutPriceService;
	@Autowired
	private GoodsDetailService goodsDetailService;
	public boolean saveCutPricePrice(CutPricePrice cutPricePrice) {
		Long gdcpId = cutPricePrice.getGdcpId();
		GoodsGdcprel goodsGdcprel = goodsGdcprelService.queryGoodsGdcprelByGdcpId(gdcpId);
		CutPrice cutPrice = cutPriceService.queryCutPriceByCpId(goodsGdcprel.getCpId());
		GoodsDetail goodsDetail = goodsDetailService.queryGoodsDetailByGdId(goodsGdcprel.getGdId());

		Long cpMostperson = cutPrice.getCpMostperson();
		Long gdPrice = goodsDetail.getGdPrice();
		Long cpSale = goodsGdcprel.getCpSale();
		int status =0;
		List<Long> list = getCutDownPrice(cpMostperson,gdPrice,cpSale);
		for(Long cutDownPrice:list){
			cutPricePrice.setCppPrice(cutDownPrice);
			status += cutPricePriceMapper.saveCutPricePrice(cutPricePrice);
		}

		return status == list.size() ? true : false;
	}
	public boolean updateCutPricePrice(CutPricePrice cutPricePrice) {
		int status = cutPricePriceMapper.updateCutPricePrice(cutPricePrice);
		return status == 1 ? true : false;
	}
	public CutPricePrice queryCutPricePriceByCppId(Long cppId) {
		return cutPricePriceMapper.queryCutPricePriceByCppId(cppId);
	}

	public Pagination<CutPricePrice> queryCutPricePriceList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CutPricePrice> page = new Pagination<CutPricePrice>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CutPricePrice> list = cutPricePriceMapper.queryCutPricePriceList(page);
		page.setData(list);
		return page;
	}
	public Pagination<CutPricePrice> queryGdcpIdList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<CutPricePrice> page = new Pagination<CutPricePrice>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CutPricePrice> list = cutPricePriceMapper.queryGdcpIdList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCutPricePriceByCppId(Long cppId) {
		int status = cutPricePriceMapper.deleteCutPricePriceByCppId(cppId);
		return status == 1 ? true : false;
	}


	public List<Long> getCutDownPrice(Long cpMostperson,Long gdPrice,Long cpSale) {
		Long cj = gdPrice-cpSale;
		Long avg = cj/cpMostperson;
		List<Long> list = new ArrayList<Long>();
		Long index = 0L;
		for(int i=0;i<cpMostperson-1;i++){
			Double  cutDownPrice= (Math.random()*0.3+0.9)*avg;
			Long cdp = Math.round(cutDownPrice);
			index += cdp;
			list.add(cdp);
		}
		Long lastone = cj-index;
		if(lastone>0){
			list.add(lastone);
			return list;
		}else{
			return getCutDownPrice( cpMostperson, gdPrice, cpSale);
		}

	}

}

