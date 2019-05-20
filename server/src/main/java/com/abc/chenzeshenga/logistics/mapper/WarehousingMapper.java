package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface WarehousingMapper extends BaseMapper<Warehousing> {
    int deleteByPrimaryKey(String warehousingNo);

//    boolean insert(Warehousing record);

    int insertSelective(Warehousing record);

    Warehousing selectByPrimaryKey(String warehousingNo);

    int updateByPrimaryKeySelective(Warehousing record);

    int updateByPrimaryKey(Warehousing record);

    List<Warehousing> listAll(Pagination pagination);

    List<Warehousing> listByOwner(Pagination pagination, String creator);

    List<Warehousing> listByOwnerAndStatus(Pagination pagination, String creator, String status);

    List<Warehousing> listByStatus(Pagination pagination, String status);

}
