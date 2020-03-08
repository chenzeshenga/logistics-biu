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
    long curr = 1;
    if (jsonNode.hasNonNull("curr")) {
      curr = jsonNode.get("curr").asLong();
    }
    int size = 10;
    if (jsonNode.hasNonNull("size")) {
      size = jsonNode.get("size").asInt();
    }
    return new Pagination(curr, size);
  }
}
