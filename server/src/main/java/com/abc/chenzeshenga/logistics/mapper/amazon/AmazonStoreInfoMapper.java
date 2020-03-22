package com.abc.chenzeshenga.logistics.mapper.amazon;

import com.abc.chenzeshenga.logistics.model.amazon.AmazonStoreInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzesheng
 * @since 2020-03-22 22:04
 */
@Mapper
public interface AmazonStoreInfoMapper {

  AmazonStoreInfo getAmazonStoreInfoByUserId(String userId);
}
