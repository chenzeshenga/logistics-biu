package com.abc.chenzeshenga.logistics.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-03-30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PageQuery {

  private String regTxt;

  @JsonProperty("page")
  private Pagination pagination;
}
