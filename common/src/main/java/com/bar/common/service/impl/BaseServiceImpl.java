package com.bar.common.service.impl;


import com.bar.common.mapper.BaseMapper;
import com.bar.common.service.BaseService;

import java.util.List;

public class BaseServiceImpl<T, E> implements BaseService<T, E> {


    private BaseMapper<T, E> baseMapper;

    protected void setBaseMapper(BaseMapper<T, E> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public int deleteByPrimaryKey(E id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public T selectByPrimaryKey(E id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return baseMapper.updateByPrimaryKey(t);
    }

    @Override
    public List<T> findByObject(T t) {
        return baseMapper.findByObject(t);
    }
}
