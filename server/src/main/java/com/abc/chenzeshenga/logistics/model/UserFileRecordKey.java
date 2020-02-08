package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * l_user_file_record
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFileRecordKey implements Serializable {

  private static final long serialVersionUID = -2786872391209228063L;

  private String uid;

  private String fileUuid;
}
