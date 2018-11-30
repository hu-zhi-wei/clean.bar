package com.bar.user.service.impl;

import com.bar.common.service.impl.BaseServiceImpl;
import com.bar.user.bean.RoleResource;
import com.bar.user.mapper.RoleResourceMapper;
import com.bar.user.service.RoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource, Integer> implements RoleResourceService {

    private RoleResourceMapper roleResourceMapper;

    @Resource
    public void setRoleResourceMapper(RoleResourceMapper roleResourceMapper) {
        this.roleResourceMapper = roleResourceMapper;
        super.setBaseMapper(roleResourceMapper);
    }
}
