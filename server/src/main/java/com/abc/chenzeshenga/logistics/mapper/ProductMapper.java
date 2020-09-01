package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据sku查找商品，不准确
     *
     * @param sku 用户sku
     * @return 商品信息
     */
    Product whetherImgsV2(String sku);

    @Override
    Integer insert(Product product);

    int insertSelective(Product product);

    int updateByPrimaryKeySelective(Product product);

    /**
     * 根据sku/dySku更新产品
     *
     * @param product 商品
     * @return 更新数量
     */
    int updateByPrimaryKeySelectiveV2(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(String sku);

    Product selectByPrimaryKey(String sku);

    List<Product> listByStatusWithUser(
            @Param("searchProduct") Product searchProduct, @Param("sqlLimit") SqlLimit sqlLimit);

    List<Product> listByStatus(
            @Param("searchProduct") Product searchProduct, @Param("sqlLimit") SqlLimit sqlLimit);

    long countByStatus(@Param("searchProduct") Product searchProduct);

    int statusUpdate(String sku, String status);

    int batchUpdate(List<String> skus);

    List<SkuLabel> listUserOwnProduct(String username);

    Product selectProductBySku(String sku);

    Product selectProductByOwnerAndDySku(String owner, String dysku);
}
