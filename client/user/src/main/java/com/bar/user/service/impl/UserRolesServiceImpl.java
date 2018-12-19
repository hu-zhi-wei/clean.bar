package com.bar.user.service.impl;

import com.bar.common.service.impl.BaseServiceImpl;
import com.bar.user.bean.UserRoles;
import com.bar.user.mapper.UserRolesMapper;
import com.bar.user.service.UserRolesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserRolesServiceImpl extends BaseServiceImpl<UserRoles, Integer> implements UserRolesService {

    private UserRolesMapper userRolesMapper;

    @Resource
    public void setUserRolesMapper(UserRolesMapper userRolesMapper) {
        this.userRolesMapper = userRolesMapper;
        super.setBaseMapper(userRolesMapper);
    }
}
