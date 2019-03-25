package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * l_user_file_record
 *
 * @author
 */
@Data @NoArgsConstructor @AllArgsConstructor public class UserFileRecord extends UserFileRecordKey implements Serializable {

    private String fileName;

    private Date createOn;

}