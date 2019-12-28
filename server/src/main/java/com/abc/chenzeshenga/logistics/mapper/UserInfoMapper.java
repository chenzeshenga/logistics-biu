package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.UserInfo;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 2019-12-28
 */
@Mapper
public interface UserInfoMapper {

    UserInfo getUserInfoById(String userId);

}