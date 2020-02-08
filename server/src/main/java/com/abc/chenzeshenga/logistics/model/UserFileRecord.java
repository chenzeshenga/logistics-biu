package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * l_user_file_record
 *
 * @author chenzeshenga
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserFileRecord extends UserFileRecordKey implements Serializable {

  private static final long serialVersionUID = 3204937508759228917L;

  private String fileName;

  private Date createOn;
}
