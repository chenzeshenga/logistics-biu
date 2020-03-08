package com.abc.chenzeshenga.logistics.model.common;

import java.util.List;
import lombok.*;

/**
 * @author chenzeshenga
 * @since 2020-03-8 22:14
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Page<T> extends Pagination {

  private List<T> data;
}
