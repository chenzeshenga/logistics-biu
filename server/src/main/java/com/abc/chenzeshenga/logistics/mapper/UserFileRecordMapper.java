package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.UserFileRecord;
import com.abc.chenzeshenga.logistics.model.UserFileRecordKey;
import org.springframework.stereotype.Repository;

@Repository public interface UserFileRecordMapper {
    int deleteByPrimaryKey(UserFileRecordKey key);

    int insert(UserFileRecord record);

    int insertSelective(UserFileRecord record);

    UserFileRecord selectByPrimaryKey(UserFileRecordKey key);

    int updateByPrimaryKeySelective(UserFileRecord record);

    int updateByPrimaryKey(UserFileRecord record);
}