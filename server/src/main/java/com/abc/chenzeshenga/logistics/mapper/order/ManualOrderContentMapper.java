package com.abc.chenzeshenga.logistics.mapper.order;

import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenzeshenga
 * @since 2020-03-28 21:45
 */
@Mapper
public interface ManualOrderContentMapper {

  List<ManualOrderContent> getManualOrderContent(String ordno);

  List<ManualOrderContent> listManualOrderContentByCategoryAndStatus(
      String category, String status);

  List<ManualOrderContent> listManualOrderContentByCategoryAndStatusUser(
      String category, String status, String creator);

  /**
   * 根据搜索条件搜索订单内容
   *
   * @param params 搜索条件
   * @return 订单内容
   */
  List<ManualOrderContent> listManualOrderContentByParamsWithoutPage(
      @Param("params") Map<String, Object> params);
}
