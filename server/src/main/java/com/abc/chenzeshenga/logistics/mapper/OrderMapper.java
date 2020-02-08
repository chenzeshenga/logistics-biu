package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.ord.OrdTrackNoMapping;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<ManualOrder> {

  int add(ManualOrder manualOrder);

  int update(ManualOrder manualOrder);

  int updateVolumeAndWeight(ManualOrder manualOrder);

  int insertContent(List<ManualOrderContent> manualOrderContents);

  List<ManualOrder> list(
      Pagination pagination,
      @Param("cname") String cname,
      @Param("type") String type,
      @Param("status") String status);

  List<ManualOrder> listByRange(
      Pagination pagination,
      @Param("cname") String cname,
      @Param("type") String type,
      @Param("status") String status,
      @Param("from") Date from,
      @Param("to") Date to,
      @Param("ordno") String ordno,
      @Param("creator") String creator,
      @Param("channelCode") String channelCode);

  int delete(String ordNo);

  int deleteContent(String ordNo);

  List<ManualOrderContent> listContent(String ordNo);

  List<ManualOrder> listAll();

  List<ManualOrderContent> listContentBatch(String ordNo);

  List<ManualOrderContent> listContent2(String ordNo);

  int statusUpdate(ManualOrder manualOrder);

  int statusUpdateBatch(Map<String, Object> request);

  int fillInTrackNo(ManualOrder manualOrder);

  int abnormal(ManualOrder manualOrder);

  Product getProduct(String sku);

  // List<ManualOrder> listAll(String status);

  List<ManualOrder> listAllByUsername(Map<String, String> request);

  int count();

  ManualOrder quickSearch(String search);

  ManualOrder getOrderSeq();

  List<ManualOrder> fetchOrderNo(String creator);

  ManualOrder getOrdDetail(String ordNo);

  int updateTrackNo(OrdTrackNoMapping ordTrackNoMapping);
}
