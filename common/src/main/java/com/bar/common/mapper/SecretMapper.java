package com.bar.common.mapper;

import com.bar.common.bean.Secret;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretMapper {
    List<Secret> findAllByReceiver(@Param("receiver") String receiver);
}
