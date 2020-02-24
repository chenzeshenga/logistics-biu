package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.service.returning.ReturnOrdContentService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.20
 */
@Service public class ReturnService extends ServiceImpl<ReturnMapper, Return> {

  private ReturnOrdContentService returnOrdContentService;

  @Autowired public ReturnService(ReturnOrdContentService returnOrdContentService) {
    this.returnOrdContentService = returnOrdContentService;
  }

  public Page<Return> list(Page page, String status, Date from, Date to) {
    return page.setRecords(baseMapper.list(page, status, from, to));
  }

  public Page<Return> listAll(Page page, String status, Date from, Date to) {
    return page.setRecords(baseMapper.listAll(page, status, from, to));
  }

  public Return getReturnOrdDetail(String returnNo) {
    Return returnOrd = selectById(returnNo);
    List<ReturnContent> returnContentList = returnOrdContentService.listContent(returnNo);
    if (!returnContentList.isEmpty()) {
      returnOrd.setContentList(returnContentList);
    }
    return returnOrd;
  }
}
