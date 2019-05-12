package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.JpAddress;
import com.abc.chenzeshenga.logistics.model.JpDetailAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file AddressMapper
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 22:22
 * @updateOn 2019-3-6 22:22
 */
@Mapper public interface AddressMapper {

    List<JpAddress> getKen();

    List<JpAddress> getCity(String kenId);

    List<JpAddress> getTown(String cityId);

    List<JpDetailAddress> getAll();

    JpDetailAddress selectByPk(String zip);
}
