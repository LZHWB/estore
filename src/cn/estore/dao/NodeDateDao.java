package cn.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.estore.domain.NodeDate;
import cn.estore.utils.DataSourceUtils;

//BeanHandler、BeanListHandler、MapHandler、MapListHandler、ScalarHandler
//传递这五个子类对象后query的返回值分别是：
// 一个JavaBean对象、
//        一个装有多个JavaBean对象的List集合对象
//        一个装有一行结果集的Map对象（也就是一个Map，Map装着的是一行结果集)
//        一个装有多个一行结果集的Map的List集合对象（也就是List里有多个Map
//        ，每个Map都是一行结果集）
//        一个Object类型（这种一般运用在查询结果只有一行一列的情况）

public class NodeDateDao {

    //获取下级目录  --参数code是fathercode
    public List<NodeDate> getNextName(String code) throws SQLException {
        String sql = "select * from category where fathercode="+code;
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<NodeDate>(NodeDate.class));
    }
    //根据名字获取NodeDate对象
    public NodeDate getNoeDate(String name) throws SQLException {
        String sql = " select * from category where name =?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<NodeDate>(NodeDate.class),name);
    }

}
