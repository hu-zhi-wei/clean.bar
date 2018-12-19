package com.bar.user.service.impl;

import com.bar.common.service.impl.BaseServiceImpl;
import com.bar.user.bean.Roles;
import com.bar.user.mapper.RolesMapper;
import com.bar.user.service.RolesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class RolesServiceImpl extends BaseServiceImpl<Roles, Integer> implements RolesService {

    private RolesMapper rolesMapper;

    @Resource
    public void setRolesMapper(RolesMapper rolesMapper) {
        this.rolesMapper = rolesMapper;
        super.setBaseMapper(rolesMapper);
    }
}
