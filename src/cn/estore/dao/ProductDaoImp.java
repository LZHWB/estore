package cn.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.estore.domain.NodeDate;
import cn.estore.domain.Product;
import cn.estore.utils.DataSourceUtils;

public class ProductDaoImp implements ProductDao {
    //查询所有商品信息
    public List<Product> findAll() throws SQLException {
        String sql = "select * from products ";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    //通过id查找唯一商品
    public Product findById(int id) throws SQLException {
        String sql = "select * from products where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new  BeanHandler<Product>(Product.class), id);
    }

    //按条件查询
    /*field为字段名称name 或者是description 、msg为为字段值*/
    public List<Product> simpleSelect(String field, String msg) throws SQLException {
        String sql = "select * from products where " + field + " like ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class), "%" + msg + "%");
    }

    //得到页码总条数:
    public int findCountByCode(String code) throws SQLException {
        String sql = "select count(*) from products where substring(c3code,1,"+code.length()+")"+"=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        long count = (long) runner.query(sql, new ScalarHandler(), code);
        return (int) count;
    }

    //根据所传值得到在该分类的商品
    //sql语句中的substring 1是从第一个字符串开始
    public List<Product> findByCode(String code) throws SQLException {
        String sql = "select * from products where substring(c3code,1,"+code.length()+")"+"=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class), code);
    }

    //根据当前的页号和每页条数
    public List<Product> findByPageByCode(int pageNum, int currentPage, String code) throws SQLException {
        String sql = "select * from products where substring(c3code,1,"+code.length()+")"+"=? limit ?,?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class), code,
                (pageNum - 1) * currentPage, currentPage);
    }

}
