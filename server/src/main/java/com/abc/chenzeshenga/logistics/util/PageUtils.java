package com.abc.chenzeshenga.logistics.util;

import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author chenzeshenga
 * @since 2020-03-08 22:35
 */
public class PageUtils {

  private PageUtils() {}

  public static Pagination getPageParam(JsonNode jsonNode) {

    return new Pagination();
  }
}
