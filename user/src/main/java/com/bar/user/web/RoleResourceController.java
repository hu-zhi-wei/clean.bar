package com.bar.user.web;

import com.bar.common.web.BaseController;
import com.bar.user.bean.RoleResource;
import com.bar.user.service.RoleResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/roleResource")
public class RoleResourceController extends BaseController<RoleResource, Integer> {

    private RoleResourceService roleResourceService;

    @Resource
    public void setRoleResourceService(RoleResourceService roleResourceService) {
        this.roleResourceService = roleResourceService;
        super.setBaseService(roleResourceService);
    }
}
