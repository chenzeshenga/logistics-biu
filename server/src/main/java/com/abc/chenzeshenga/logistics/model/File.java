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

  private byte[] file;

  private String fileName;

  public File(String uuid, byte[] file) {
    this.uuid = uuid;
    this.file = file;
  }

  private static final long serialVersionUID = 1L;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }
}
