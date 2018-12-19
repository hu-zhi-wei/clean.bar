package com.bar.user.web;

import com.bar.common.web.BaseController;
import com.bar.user.bean.Roles;
import com.bar.user.service.RolesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/roles")
public class RolesController extends BaseController<Roles, Integer> {

    private RolesService rolesService;

    @Resource
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
        super.setBaseService(rolesService);
    }
}
