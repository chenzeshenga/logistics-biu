package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.TrackNo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrackNoMapper extends BaseMapper<TrackNo> {

  List<TrackNo> list(Pagination pagination);

  List<TrackNo> carrierNo(Pagination pagination, String carrierNo, String channelCode);

  List<TrackNo> listByCarrierNo(Pagination pagination, String carrierNo);

  int add(TrackNo trackNo);

  int delete(int id);

  int update(TrackNo trackNo);

  TrackNo generate();

  TrackNo generate3(String channelCode);

  TrackNo generate2(String carrier);
}
