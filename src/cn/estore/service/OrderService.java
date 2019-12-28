package cn.estore.service;

import java.sql.SQLException;

import cn.estore.dao.OrderDao;
import cn.estore.domain.Order;

public class OrderService {
    public int addOrder(Order order){
        OrderDao dao=new OrderDao();
        int i = 0;
        try {
            i = dao.addOrder(order);
        } catch (SQLException e) {
            System.out.println("订单提交失败!");
        }
        return i;
    }


}
