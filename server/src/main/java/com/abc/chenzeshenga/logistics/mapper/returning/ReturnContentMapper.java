package com.abc.chenzeshenga.logistics.mapper.returning;

import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-02-24 22:27
 */
@Mapper
public interface ReturnContentMapper extends BaseMapper<ReturnContent> {

  List<ReturnContent> listContent(String returnNo);
}
