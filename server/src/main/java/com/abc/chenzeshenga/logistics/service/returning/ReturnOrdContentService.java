package com.abc.chenzeshenga.logistics.service.returning;

import com.abc.chenzeshenga.logistics.mapper.returning.ReturnContentMapper;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-02-24 22:22
 */
@Service
public class ReturnOrdContentService extends ServiceImpl<ReturnContentMapper, ReturnContent> {

  public List<ReturnContent> listContent(String returnNo) {
    return baseMapper.listContent(returnNo);
  }
}
