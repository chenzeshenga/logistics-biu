package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file LabelMapper
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 1:18
 * @updateOn 2019-3-6 1:18
 */
@Mapper public interface LabelMapper {

    List<Label> list();

}
