package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.WarehousingContentMapper;
import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.WarehousingReq;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Service
@SuppressWarnings("rawtypes")
public class WarehousingService extends ServiceImpl<WarehousingMapper, Warehousing> {

  @Resource private WarehousingContentMapper warehousingContentMapper;

  private UserCommonService userCommonService;

  @Autowired
  public WarehousingService(UserCommonService userCommonService) {
    this.userCommonService = userCommonService;
  }

  public Page<Warehousing> listAll(Page page, String method) {
    return page.setRecords(baseMapper.listAll(page, method));
  }

  public Page<Warehousing> listByOwner(Page page, String creator, String method) {
    return page.setRecords(baseMapper.listByOwner(page, creator, method));
  }

  public Page<Warehousing> listByOwnerAndStatus(
      Page page, String creator, String status, String method) {
    return page.setRecords(baseMapper.listByOwnerAndStatus(page, creator, status, method));
  }

  public Page<Warehousing> listByStatus(Page page, String status, String method) {
    return page.setRecords(baseMapper.listByStatus(page, status, method));
  }

  public com.abc.chenzeshenga.logistics.model.common.Page<Warehousing> listV2(
      PageQueryEntity<WarehousingReq> warehousingReqPageQueryEntity) {
    WarehousingReq warehousingReq = warehousingReqPageQueryEntity.getEntity();
    warehousingReq.setCategory(switchMethod(warehousingReq.getCategory()));
    if (userCommonService.isManagerRole(UserUtils.getUserName())) {
      warehousingReq.setCreator("");
    } else {
      warehousingReq.setCreator(UserUtils.getUserName());
    }
    Pagination pagination = warehousingReqPageQueryEntity.getPagination();
    long fromIdx = (pagination.getCurrent() - 1) * (long) pagination.getSize();
    long toIdx = pagination.getCurrent() * (long) pagination.getSize();
    warehousingReq.setFromIdx(fromIdx);
    warehousingReq.setToIdx(toIdx);
    List<Warehousing> warehousingList = baseMapper.listV2(warehousingReq);
    int total = baseMapper.count(warehousingReq);
    for (Warehousing warehousing : warehousingList) {
      warehousing.setWarehousingContentList(
          warehousingContentMapper.listContent(warehousing.getWarehousingNo()));
    }
    com.abc.chenzeshenga.logistics.model.common.Page<Warehousing> warehousingPage =
        new com.abc.chenzeshenga.logistics.model.common.Page<>();
    warehousingPage.setData(warehousingList);
    warehousingPage.setCurrent(pagination.getCurrent());
    warehousingPage.setTotal(total);
    warehousingPage.setSize(pagination.getSize());
    return warehousingPage;
  }

  public Page<Warehousing> listByOwnerAndStatusAndFilter(
      Page page, String creator, String status, String method, WarehousingReq warehousingReq) {
    return page.setRecords(
        baseMapper.listByOwnerAndStatusAndFilter(page, creator, status, method, warehousingReq));
  }

  public Page<Warehousing> listByStatusAndFilter(
      Page page, String status, String method, WarehousingReq warehousingReq) {
    return page.setRecords(baseMapper.listByStatusAndFilter(page, status, method, warehousingReq));
  }

  private String switchMethod(String method) {
    switch (method) {
      case "2":
        return "其他头程";
      case "1":
      default:
        return "东岳头程";
    }
  }
}
