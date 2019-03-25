package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * l_user_file_record
 *
 * @author
 */
@Data @AllArgsConstructor @NoArgsConstructor public class UserFileRecordKey implements Serializable {

    private String uid;

    private String fileUuid;

}