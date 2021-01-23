package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.model.claim.ClaimContentDealing;
import com.abc.chenzeshenga.logistics.model.claim.ClaimPackage;
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

  /**
   * select by pk
   *
   * @param returnNo pk
   * @return 退货单
   */
  Return selectByPk(String returnNo);

  /**
   * 创建退货订单
   *
   * @param returnOrd 订单内容
   * @return influence
   */
  int newReturnOrd(@Param("returnOrd") Return returnOrd);

  int updateByPk(Return returnOrder);

  /**
   * 新增退货订单
   *
   * @param returnOrder 退货订单
   * @return 1
   */
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
   * @param returnEntity 查询参数 分别为 订单状态/创建者/创建时间区间
   * @param sqlLimit     分页参数
   * @return 退货单列表
   */
  List<Return> listV2(@Param("entity") Return returnEntity, @Param("limit") SqlLimit sqlLimit);

  /**
   * count
   *
   * @param returnEntity 查询参数 分别为 订单状态/创建者/创建时间区间
   * @return total
   */
  long count(@Param("entity") Return returnEntity);

  /**
   * 根据退货单号认领退货单
   *
   * @param returnNo 退货单号
   * @param owner    属主
   * @return update
   */
  int claimReturnNo(@Param("returnNo") String returnNo, @Param("owner") String owner);

  /**
   * 将创建时间在 14天的退货单的状态更新为 历史
   *
   * @return update
   */
  int archiveReturnOrd();

  /**
   * 根据退货单号获取退货单内容
   *
   * @param returnNo 退货单号
   * @return 退货单内容
   */
  List<ReturnContent> selectContentById(String returnNo);

  /**
   * 根据退货单号获取退货单包裹信息
   *
   * @param returnNo 退货单号
   * @return 退货单包裹信息
   */
  List<ClaimPackage> selectClaimPackageById(String returnNo);

  /**
   * 根据退货单号获取退货货物处理方式
   *
   * @param returnNo 退货单号
   * @return 处理方式列表
   */
  List<ClaimContentDealing> selectContentDealingById(String returnNo);

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

  /**
   * 根据退货单号删除退货单
   *
   * @param returnNo 退货单号
   * @return 删除的数量
   */
  int dropReturnOrd(String returnNo);

  /**
   * 根据退货单号删除退货包裹
   *
   * @param returnNo 退货单号
   * @return influence
   */
  int dropClaimPackage(@Param("returnNo") String returnNo);

  /**
   * 插入退货包裹列表
   *
   * @param claimPackageList 退货包裹列表
   * @return influence
   */
  int insertClaimPackage(@Param("list") List<ClaimPackage> claimPackageList);

  /**
   * 根据退货单号修改状态
   *
   * @param returnNo 退货单号
   * @param status   状态
   * @return influence
   */
  int updateStatus(@Param("returnNo") String returnNo, @Param("status") String status);

  /**
   * 根据退货单号删除原处理内容
   *
   * @param returnNo 退货单号
   * @return influence
   */
  int dropClaimContentDealing(@Param("returnNo") String returnNo);

  /**
   * 插入退货单处理列表
   *
   * @param claimContentDealingList 退货单处理列表
   * @return influence
   */
  int insertClaimContentDealing(@Param("list") List<ClaimContentDealing> claimContentDealingList);
}
