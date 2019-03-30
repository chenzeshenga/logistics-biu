package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Mapper public interface ProductMapper extends BaseMapper<Product> {

    List<SkuLabel> list(String username);

    List<SkuLabel> listAll();

    int addImg(Product product);

    Product whetherImgs(String sku);

    Product whetherImgsV2(String sku);

    @Override Integer insert(Product product);

    int insertSelective(Product product);

    int updateByPrimaryKeySelective(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(String sku);

    Product selectByPrimaryKey(String sku);

    List<Product> listByStatusWithUser(Page page, String username, String status);

    List<Product> listByStatus(Page page, String status);

    int statusUpdate(String sku, String status);

    int batchUpdate(List<String> skus);

}
