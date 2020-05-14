package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.WarehousingReq;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehousingMapper extends BaseMapper<Warehousing> {
  int deleteByPrimaryKey(String warehousingNo);

  // boolean insert(Warehousing record);

  int insertSelective(Warehousing record);

  Warehousing selectByPrimaryKey(String warehousingNo);

  int updateByPrimaryKeySelective(Warehousing record);

  int updateByPrimaryKey(Warehousing record);

  List<Warehousing> listAll(Pagination pagination, String method);

  List<Warehousing> listByOwner(Pagination pagination, String creator, String method);

  List<Warehousing> listByOwnerAndStatus(
      Pagination pagination, String creator, String status, String method);

  List<Warehousing> listByOwnerAndStatusAndFilter(
      Pagination pagination,
      String creator,
      String status,
      String method,
      WarehousingReq warehousingReq);

  List<Warehousing> listV2(WarehousingReq warehousingReq);

  Integer count(WarehousingReq warehousingReq);

  List<Warehousing> listByStatus(Pagination pagination, String status, String method);

  List<Warehousing> listByStatusAndFilter(
      Pagination pagination, String status, String method, WarehousingReq warehousingReq);

  Warehousing getWarehousingSeq();

  int statusUpdate(String warehousingNo, String from, String to, String username, Date curr);

  Warehousing fetchByWarehousingNo(String warehousingNo);
}
