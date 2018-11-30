package com.bar.user.mapper;

import com.bar.common.mapper.BaseMapper;
import com.bar.user.bean.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesMapper extends BaseMapper<Roles, Integer> {
}