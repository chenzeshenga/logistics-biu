package com.abc.chenzeshenga.logistics.util;

import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;

/**
 * @author chenzeshenga
 * @since 2020-03-08 23:23
 */
public class SqlUtils {

  private SqlUtils() {}

  public static SqlLimit generateSqlLimit(Pagination pagination) {
    int size = pagination.getSize();
    long curr = pagination.getCurrent();
    return new SqlLimit((curr - 1) * size, size);
  }


}
