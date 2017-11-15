package com.retailers.dht.manage.job;

import com.retailers.dht.common.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台管理定时任务
 * @author zhongp
 * @version 1.0.1
 * @data 2017-10-07
 */
@Service("mTaskJob")
public class ManageTaskJob {
    Logger logger = LoggerFactory.getLogger(ManageTaskJob.class);
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 每小时清除未被使用的附件资源
     */
    public void clearUnUsedAttachemnt(){
        logger.info("附件资源清除执行开始");
        attachmentService.clearUnUsedAttachemnt();
        logger.info("附件资源清除执行完毕");
    }
}