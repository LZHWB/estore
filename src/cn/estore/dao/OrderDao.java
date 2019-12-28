package cn.estore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.estore.domain.Order;
import cn.estore.utils.DataSourceUtils;
//runner.update的返回值>0才是成功的

public class OrderDao {

    //添加订单操作
    public int addOrder(Order order) throws SQLException {
        String sql="insert into orders values(null,?,?,?,null,null)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.update(sql,order.getMoney(),order.getReceiverinfo(),order.getPaystate());
    }

}
