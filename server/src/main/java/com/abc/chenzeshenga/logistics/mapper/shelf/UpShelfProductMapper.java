package com.abc.chenzeshenga.logistics.mapper.shelf;

import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-01-23 23:25
 */
@Mapper
public interface UpShelfProductMapper extends BaseMapper<UpShelfProduct> {

    UpShelfProduct selectOneBySku(String sku, String owner);

    @Override
    Integer insert(UpShelfProduct upShelfProduct);

    int removeWarehouse4PickUp(String sku, Integer num, String owner, String shelfNo);
}
