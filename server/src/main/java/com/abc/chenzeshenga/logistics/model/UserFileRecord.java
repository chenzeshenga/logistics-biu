package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * l_user_file_record
 *
 * @author chenzeshenga
 */
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = false) public class UserFileRecord extends UserFileRecordKey
    implements Serializable {

    private String fileName;

    private Date createOn;

}
