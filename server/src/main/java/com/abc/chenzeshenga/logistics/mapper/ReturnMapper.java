package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<Return> list(Pagination pagination, @Param("status") String status, @Param("from") Date from,
        @Param("to") Date to);

    List<Return> listAll(Pagination pagination, @Param("status") String status, @Param("from") Date from,
        @Param("to") Date to);

}
