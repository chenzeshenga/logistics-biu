package com.abc.chenzeshenga.logistics.service.dict.impl;

import com.abc.chenzeshenga.logistics.mapper.dict.DictMapper;
import com.abc.chenzeshenga.logistics.model.dict.Dict;
import com.abc.chenzeshenga.logistics.service.dict.IDictService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-12-01 21:46
 */
@Service
@Slf4j
public class DictServiceImpl implements IDictService {

  @Resource private DictMapper dictMapper;

  @Override
  public List<Dict> get() {
    return dictMapper.get();
  }

  @Override
  public Dict getById(int id) {
    return dictMapper.getById(id);
  }

  @Override
  public Dict getByKey(String key) {
    return dictMapper.getByKey(key);
  }

  @Override
  public int insert(Dict dict) {
    return dictMapper.insert(dict);
  }

  @Override
  public int delete(int id) {
    return dictMapper.delete(id);
  }
}
