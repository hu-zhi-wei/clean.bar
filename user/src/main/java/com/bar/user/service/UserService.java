package com.bar.user.service;

import com.bar.common.service.BaseService;
import com.bar.user.bean.Resources;
import com.bar.user.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface UserService extends BaseService<User, Integer> {

    User login(User user, HttpServletRequest request);

    User findByUserName(String userName);

    Set<Resources> getResourcesByUserId(Integer id);

    default String defaultFunction() {
        System.out.println("wo shi default function!!!");
               return "default function";
            }
    static String get(){
        return "hello word";
    }
}
