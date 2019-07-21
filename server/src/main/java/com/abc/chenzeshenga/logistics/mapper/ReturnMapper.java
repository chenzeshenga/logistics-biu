package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.19
 */
@Mapper public interface ReturnMapper extends BaseMapper<Return> {

    Return getReturnSeq();

    Return selectByPk(String returnNo);

    int updateByPk(Return returnOrder);

    int insertReturnOrder(Return returnOrder);

    int insertContent(List<ReturnContent> returnContentList);

    int deleteContent(String returnNo);

    List<Return> list(String status, String creator, Date from, Date to);

    List<Return> listAll(String status, Date from, Date to);

}
