
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.SysParameterConfig;
import java.util.Map;
/**
 * 描述：系统参数配置表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-08 11:21:04
 */
public interface SysParameterConfigService {

	/**
	 * 添加系统参数配置表
	 * @param sysParameterConfig
	 * @return
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public boolean saveSysParameterConfig(SysParameterConfig sysParameterConfig);
	/**
	 * 编辑系统参数配置表
	 * @param sysParameterConfig
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateSysParameterConfig(SysParameterConfig sysParameterConfig);
	/**
	 * 根据id查询系统参数配置表
	 * @param parameterKey
	 * @return sysParameterConfig
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public SysParameterConfig querySysParameterConfigByParameterKey(String parameterKey);
	/**
	 * 查询系统参数配置表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-08 11:21:04
	 */
	public Pagination<SysParameterConfig> querySysParameterConfigList(Map<String, Object> params, int pageNo, int pageSize);

	/**
	 * 初始化系统常配置
	 */
	public void initSysParamter();

}


