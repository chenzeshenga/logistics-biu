package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Mapper public interface OrderMapper {

    int add(ManualOrder manualOrder);

    int insertContent(List<ManualOrderContent> manualOrderContents);

}
