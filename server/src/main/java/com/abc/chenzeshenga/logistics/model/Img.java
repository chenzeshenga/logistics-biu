package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * l_img
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img implements Serializable {

  private static final long serialVersionUID = -2428746817170082687L;

  private String uuid;

  private byte[] img;
}
