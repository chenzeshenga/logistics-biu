package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * l_file
 *
 * @author chenzesheng
 * @since 2020-10-22
 * @deprecated 2021-03-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class File implements Serializable {
    private String uuid;

    private byte[] userFile;

    private String fileName;

    private Date upTime;

    public File(String uuid, byte[] userFile) {
        this.uuid = uuid;
        this.userFile = userFile;
    }

    public File(String uuid, byte[] userFile, String fileName) {
        this.uuid = uuid;
        this.userFile = userFile;
        this.fileName = fileName;
    }
}
