package com.abc.chenzeshenga.logistics.mapper.dict;

import com.abc.chenzeshenga.logistics.model.dict.Dict;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenzeshenga
 * @since 2020-12-01 21:49
 */
@Mapper
public interface DictMapper {

  /**
   * 获取所有字典礼拜
   *
   * @return 字典列表
   */
  List<Dict> get();

  /**
   * 根据id获取字典内容
   *
   * @param id id
   * @return 字典
   */
  Dict getById(@Param("id") Integer id);

  Dict getByKey(@Param("key") String key);

  /**
   * 插入字典数据
   *
   * @param dict 字典
   * @return 更新数量
   */
  int insert(@Param("dict") Dict dict);

  /**
   * 删除字典数据
   *
   * @param id id
   * @return 更新数量
   */
  int delete(@Param("id)") Integer id);
}
