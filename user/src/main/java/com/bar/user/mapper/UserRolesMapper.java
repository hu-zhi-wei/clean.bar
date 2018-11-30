package com.bar.user.mapper;

import com.bar.common.mapper.BaseMapper;
import com.bar.user.bean.UserRoles;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesMapper extends BaseMapper<UserRoles, Integer> {
}