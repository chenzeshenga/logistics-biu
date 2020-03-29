package com.abc.chenzeshenga.logistics.mapper.amazon;

import com.abc.chenzeshenga.logistics.model.amazon.AmazonStoreInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzesheng
 * @since 2020-03-22 22:04
 */
@Mapper
public interface AmazonStoreInfoMapper {

  AmazonStoreInfo getAmazonStoreInfoByUserId(String userId);

  AmazonStoreInfo getAmazonOrderInfoBySellerId(String sellerId);

  int addStoreInfo(AmazonStoreInfo amazonStoreInfo);

  List<AmazonStoreInfo> listAll(String regTxt, long from, long to);

  List<AmazonStoreInfo> listAllByUserName(String username, String regTxt, long from, long to);
}
