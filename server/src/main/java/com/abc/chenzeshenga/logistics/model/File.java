package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * l_file
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {
  private String uuid;

  private byte[] userFile;

  private String fileName;

  public File(String uuid, byte[] userFile) {
    this.uuid = uuid;
    this.userFile = userFile;
  }
}
