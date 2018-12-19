package com.bar.user.web;

import com.bar.common.web.BaseController;
import com.bar.user.bean.UserRoles;
import com.bar.user.service.UserRolesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userRoles")
public class UserRolesController extends BaseController<UserRoles, Integer> {

    private UserRolesService userRolesService;

    @Resource
    public void setUserRolesService(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
        super.setBaseService(userRolesService);
    }
}
