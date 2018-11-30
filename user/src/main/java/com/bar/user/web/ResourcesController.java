package com.bar.user.web;

import com.bar.common.web.BaseController;
import com.bar.user.bean.Resources;
import com.bar.user.service.ResourcesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resources")
public class ResourcesController extends BaseController<Resources, Integer> {

    private ResourcesService resourcesService;

    @Resource
    public void setResourcesService(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
        super.setBaseService(resourcesService);
    }
}
