
package com.retailers.mybatis.common.service;

import com.retailers.mybatis.common.entity.Attachment;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 描述：系统附件表(用于存放上传物品)Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-14 15:23:57
 */
public interface AttachmentService {

	/**
	 * 添加系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public boolean saveAttachment(Attachment attachment);
	/**
	 * 编辑系统附件表(用于存放上传物品)
	 * @param attachment
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateAttachment(Attachment attachment);
	/**
	 * 根据id查询系统附件表(用于存放上传物品)
	 * @param id
	 * @return attachment
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public Attachment queryAttachmentById(Long id);
	/**
	 * 查询系统附件表(用于存放上传物品)列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public Pagination<Attachment> queryAttachmentList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除系统附件表(用于存放上传物品)
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-09-14 15:23:57
	 */
	public boolean deleteAttachmentById(Long id);

	/**
	 * 编辑附件属性
	 * @param attachmentId 商品id
	 * @return
	 */
	public boolean editorAttachment(List<Long> attachmentId);

	/**
	 * 设置附件状态
	 * @param attachmentIds 附件ids
	 * @param status 状态值
	 * @return
	 */
	public boolean editorAttachment(List<Long> attachmentIds, Long status);

	/**
	 * 编辑附件属性
	 * @param attachmentId 附件id
	 * @return
	 */
	public boolean editorAttachment(Long attachmentId);

	/**
	 * 设置附件状态
	 * @param attachmentId 附件id
	 * @param status 状态
	 * @return
	 */
	public boolean editorAttachment(Long attachmentId, Long status);

	/**
	 * 清除未被使用的附件
	 * @return
	 */
	public void clearUnUsedAttachemnt();
}


