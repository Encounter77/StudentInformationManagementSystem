package com.bcxtm.solution.service;


import com.bcxtm.solution.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟用户服务
 */
public class UserService {

    private List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
        User admin = new User("201501", "admin", "admin", "admin");
        userList.add(admin);
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * 登录校验
     * @param name 登录名
     * @param password 密码
     * @return 是否登录成功
     */
    public boolean loginCheck(String name, String password){
        for (User el : this.getUserList()) {
            if (el.getUserName().equals(name) && el.getUserPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User accessUser(String name){
        for (User el : this.getUserList()) {
            if (el.getUserName().equals(name) ) {
                return el;
            }
        }
        return null;
    }
}
