package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

  List<SkuLabel> list(String username);

  List<SkuLabel> listAll();

  List<SkuLabel> listAllByUser(String username);

  int addImg(Product product);

  Product whetherImgs(String sku);

  Product whetherImgsV2(String sku);

  @Override
  Integer insert(Product product);

  int insertSelective(Product product);

  int updateByPrimaryKeySelective(Product product);

  int updateByPrimaryKey(Product product);

  int deleteByPrimaryKey(String sku);

  Product selectByPrimaryKey(String sku);

  List<Product> listByStatusWithUser(
      Page page, String username, String status, String sku, String dySku, String name);

  List<Product> listByStatus(
      Page page, String status, String sku, String dySku, String name, String creator);

  int statusUpdate(String sku, String status);

  int batchUpdate(List<String> skus);

  List<SkuLabel> listUserOwnProduct(String username);
}
