package com.bar.user.service.impl;

import com.bar.common.number.RandomUtil;
import com.bar.common.service.impl.BaseServiceImpl;
import com.bar.user.bean.Resources;
import com.bar.user.bean.User;
import com.bar.user.mapper.UsersMapper;
import com.bar.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    private UsersMapper usersMapper;

    @Resource
    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
        super.setBaseMapper(usersMapper);
    }



    @Override
    public User login(User user, HttpServletRequest request) {
        //添加用户认证信息
        user.setPassword(RandomUtil.getMd5(user.getUserName(), user.getPassword()));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
//        try {
            subject.login(token);
            User loginUser = (User)subject.getPrincipal();
            return loginUser;
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            System.out.println("登录失败");
//        }
    }

    @Override
    public User findByUserName(String userName) {
        return usersMapper.findByUserName(userName);
    }

    @Override
    public Set<Resources> getResourcesByUserId(Integer id) {
        return usersMapper.getResourcesByUserId(id);
    }

}
