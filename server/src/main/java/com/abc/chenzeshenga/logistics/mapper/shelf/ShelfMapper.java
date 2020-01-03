package com.abc.chenzeshenga.logistics.mapper.shelf;

import java.util.List;

import com.abc.chenzeshenga.logistics.model.shelf.Shelf;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-01-01 23:38
 * 
 */
@Mapper
public interface ShelfMapper extends BaseMapper<Shelf> {

    List<Shelf> list(String reg, Pagination page);

}