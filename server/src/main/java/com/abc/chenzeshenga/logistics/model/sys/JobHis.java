package com.abc.chenzeshenga.logistics.model.sys;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author chenzeshenga
 */
@Data
@TableName("l_job_his")
public class JobHis {

    /**
     * uuid
     */
    @TableId(type = IdType.UUID)
    private String uuid;
    /**
     * 触发者
     */
    private String userId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务开始时间
     */
    private Date startTime;
    /**
     * 任务结束时间
     */
    private Date endTime;

}
