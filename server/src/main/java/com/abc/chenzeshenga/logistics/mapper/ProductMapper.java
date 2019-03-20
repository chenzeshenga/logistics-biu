package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.SkuLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper public interface ProductMapper {

    List<SkuLabel> list(String username);

    List<SkuLabel> listAll();

    int addImg(Product product);

    Product whetherImgs(String sku);

    int insert(Product product);

    int insertSelective(Product product);

    int updateByPrimaryKeySelective(Product product);

    int updateByPrimaryKey(Product product);

    int deleteByPrimaryKey(Product product);

    Product selectByPrimaryKey(String sku);

}
