package cn.estore.dao;

import java.sql.SQLException;
import java.util.List;

import cn.estore.domain.NodeDate;
import cn.estore.domain.Product;
//商品的DAO接口 用于数据库CRUD
public interface ProductDao {
    //查询所有商品信息
    public List<Product> findAll() throws SQLException;
    //通过id查找唯一商品  编辑商品信息
    public Product findById(int id) throws SQLException;
    //按条件查询
    public List<Product> simpleSelect(String field, String msg) throws SQLException;
    //得到所属分类页码总条数:
    public int findCountByCode(String code) throws SQLException;
    //根据所传值得到在该分类的商品
    public List<Product> findByCode(String code) throws SQLException;
}
