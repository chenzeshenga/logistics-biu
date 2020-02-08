package com.abc.chenzeshenga.logistics.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode(of = "value")
public class AddressLabel {

  private String label;

  private String value;

  private String zip;

  private List<AddressLabel> children;
}
