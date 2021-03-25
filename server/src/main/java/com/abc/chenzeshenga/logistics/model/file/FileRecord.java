package com.abc.chenzeshenga.logistics.model.file;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 文件信息
 *
 * @author chenzeshenga
 */
@Data
@TableName("l_file_record")
public class FileRecord {

    /**
     * uuid
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String uuid;

    /**
     * 文件原始名称
     */
    private String fileName;

    /**
     * 文件路径,包含文件名
     */
    private String absolutePath;

    /**
     * 上传用户
     */
    private String uploadUser;

    /**
     * 上传时间
     */
    private Date uploadDate;

}
