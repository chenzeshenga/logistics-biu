package com.abc.chenzeshenga.logistics.model.common;

import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzesheng
 * @since 2020-05-02 09:18
 * @param <T> 对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageQueryEntity<T> {

  private T entity;
  private Pagination pagination;
}
