package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.19
 */
@Mapper
public interface ReturnMapper extends BaseMapper<Return> {

    Return getReturnSeq();

    Return selectByPk(String returnNo);

    int updateByPk(Return returnOrder);

    int insertReturnOrder(Return returnOrder);

    int insertContent(List<ReturnContent> returnContentList);

    int deleteContent(String returnNo);

    List<Return> list(
            Pagination pagination,
            @Param("status") String status,
            @Param("from") Date from,
            @Param("to") Date to);

    /**
     * 查询退货单数据
     *
     * @param returnEntity 查询参数 分别为
     *                     订单状态/创建者/创建时间区间
     * @param sqlLimit     分页参数
     * @return 退货单列表
     */
    List<Return> listV2(@Param("entity") Return returnEntity, @Param("limit") SqlLimit sqlLimit);

    /**
     * count
     *
     * @param returnEntity 查询参数 分别为
     *                     订单状态/创建者/创建时间区间
     * @return total
     */
    long count(@Param("entity") Return returnEntity);

    List<ReturnContent> selectContentById(String returnNo);

    List<Return> listAll(
            Pagination pagination,
            @Param("status") String status,
            @Param("from") Date from,
            @Param("to") Date to);

    /**
     * @param pagination
     * @param status
     * @param from
     * @param to
     * @return
     */
    List<Return> listAllV2(
            Pagination pagination,
            @Param("status") String status,
            @Param("from") Date from,
            @Param("to") Date to);

    int updatePkgInfo(Return returnOrd);
}
