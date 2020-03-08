package com.abc.chenzeshenga.logistics.service.returning;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.common.Page;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.util.PageUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-03-08 22:17
 */
@Service
public class ReturnCommonService {

  @Resource private ReturnMapper returnMapper;

  private ObjectMapper objectMapper;

  public ReturnCommonService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Page<Return> list(String req, String type, String status) throws IOException {
    JsonNode jsonNode = objectMapper.readTree(req);
    Pagination pagination = PageUtils.getPageParam(jsonNode);

    return new Page<>();
  }
}
