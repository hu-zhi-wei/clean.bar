package com.bar.common.web;


import com.bar.common.bean.ResultBean;
import com.bar.common.custom.constant.ServiceCode;
import com.bar.common.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseController<T, E> {

    private BaseService baseService;

    protected void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public ResultBean<?> selectByPrimaryKey(E id){
        return new ResultBean<>(ServiceCode.OK, ServiceCode.OK_DEFAULT_MSG, baseService.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultBean<?> save(T t){
        baseService.insert(t);
        return new ResultBean<>(ServiceCode.OK, ServiceCode.OK_DEFAULT_MSG, null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultBean<?> edit(T t){
        baseService.updateByPrimaryKey(t);
        return new ResultBean<>(ServiceCode.OK, ServiceCode.OK_DEFAULT_MSG, null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultBean<?> delete(@RequestParam("id") E e){
        baseService.deleteByPrimaryKey(e);
        return new ResultBean<>(ServiceCode.OK, ServiceCode.OK_DEFAULT_MSG, null);
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public ResultBean<?> selectAll(){
        return new ResultBean<>(ServiceCode.OK, ServiceCode.OK_DEFAULT_MSG, baseService.selectAll());
    }

    @RequestMapping(value = "/findByObject", method = RequestMethod.POST)
    public ResultBean<?> findByObject(T t){
        return new ResultBean<>(ServiceCode.OK, ServiceCode.OK_DEFAULT_MSG, baseService.findByObject(t));
    }
}
