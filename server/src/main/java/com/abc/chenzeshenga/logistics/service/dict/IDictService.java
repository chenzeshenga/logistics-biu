package com.abc.chenzeshenga.logistics.service.dict;

import com.abc.chenzeshenga.logistics.model.dict.Dict;
import java.util.List;

/**
 * @author chenzeshenga
 * @since 2020-12-01 21:47
 */
public interface IDictService {

  /**
   * 获取所有字典内容
   *
   * @return 字典列表
   */
  List<Dict> get();

  /**
   * 根据id获取字典
   *
   * @param id id
   * @return 字典
   */
  Dict getById(int id);

  /**
   * 根据key获取字典内容
   *
   * @param key 键值
   * @return 字典
   */
  Dict getByKey(String key);

  /**
   * 插入
   *
   * @param dict 字典
   * @return 更新数量
   */
  int insert(Dict dict);

  /**
   * 根据id删除字典
   *
   * @param id id
   * @return 更新数量
   */
  int delete(int id);
}
