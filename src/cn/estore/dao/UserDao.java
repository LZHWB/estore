package cn.estore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.estore.domain.User;
import cn.estore.utils.DataSourceUtils;

public class UserDao {
    //注册操作
    public int addUser(User user) throws SQLException {

        String sql = "insert into users values(null,?,?,?,?,?,?,null)";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

       return runner.update(sql, user.getUsername(),user.getPassword(),user.getEmail(), "user", 1, user.getActivecode());
    }

    //检查是否含有重名
    public User addUserValidate(String username) throws SQLException{
        String sql="select * from users where username=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<User>(User.class), username);
    }

    //登录操作
    public User findUserByLogin(String username, String password) throws SQLException {
        if (username.contains("@")) {

            String sql = "select * from users where email=? and password=?";
            QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
            return runner.query(sql, new BeanHandler<User>(User.class), username,
                    password);

        }else {
            String sql = "select * from users where username=? and password=?";
            QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
            return runner.query(sql, new BeanHandler<User>(User.class), username,
                    password);
        }
    }


}
