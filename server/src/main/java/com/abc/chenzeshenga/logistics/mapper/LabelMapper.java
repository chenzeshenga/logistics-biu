package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper public interface LabelMapper {

    List<Label> list();

}
