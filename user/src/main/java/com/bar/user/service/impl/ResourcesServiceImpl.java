package com.bar.user.service.impl;

import com.bar.common.service.impl.BaseServiceImpl;
import com.bar.user.bean.Resources;
import com.bar.user.mapper.ResourcesMapper;
import com.bar.user.service.ResourcesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ResourcesServiceImpl extends BaseServiceImpl<Resources, Integer> implements ResourcesService {

    private ResourcesMapper resourcesMapper;

    @Resource
    public void setResourcesMapper(ResourcesMapper resourcesMapper) {
        this.resourcesMapper = resourcesMapper;
        super.setBaseMapper(resourcesMapper);
    }
}
