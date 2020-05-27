package com.abc.chenzeshenga.logistics.model.warehouse;

import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-01-29 23:57
 */
@Data
@TableName("l_up_shelf_product")
public class ProductInWarehouseSummary {

  private String dySku;
  private String name;
  private String num;
  private String owner;
  private String shelfNo;
  private String warehousingNo;
  private String totalWarehousingTime;
  private List<UpShelfProduct> children = new ArrayList<>();
  private List<ProductOutWarehouse> productOutWarehouseList = new ArrayList<>();
}
