package com.abc.chenzeshenga.logistics.model.shelf;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-01-01 23:33
 */
@Data
@TableName(value = "l_shelf")
public class Shelf {

  /** 货架号 */
  private String shelfNo;
  /** 货架区域 */
  private String area;
  /** 第几行 */
  private String rowNo;
  /** 第几层 */
  private String layer;

  private boolean enable;
}
