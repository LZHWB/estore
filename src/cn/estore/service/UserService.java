package cn.estore.service;

import java.sql.SQLException;

import cn.estore.dao.UserDao;
import cn.estore.domain.User;

import javax.security.auth.login.LoginException;


public class UserService {
    //注册操作
    public int regist(User user) {
        UserDao dao = new UserDao();
        int i = 0;
        try {
           User user1=dao.addUserValidate(user.getUsername());
           if(user1!=null){
              i=0;
               System.out.println("有重名");
           }else {
               //添加注册用户信息
               i = dao.addUser(user);
           }
        } catch (SQLException e) {
            System.out.println("注册失败");
        }
        return i;
    }

    //登录操作
    public User login(String username, String password){
        // 需要注意用户是否激活
        UserDao dao = new UserDao();
        User user = null;
        try {
            user = dao.findUserByLogin(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            // 判断用户是否激活
            if (user.getState() == 1) {
                return user;
            } else {
                user = null;
                return user;
            }
        } else {
            return user;
        }
    }
}



