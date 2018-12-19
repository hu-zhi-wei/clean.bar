package com.bar.user.mapper;

import com.bar.common.mapper.BaseMapper;
import com.bar.user.bean.Resources;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesMapper extends BaseMapper<Resources, Integer> {
}