package com.abc.chenzeshenga.logistics.mapper.order;

import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-03-28 21:45
 */
@Mapper
public interface ManualOrderContentMapper {

  List<ManualOrderContent> getManualOrderContent(String ordno);
}
