package cn.estore.service;

import java.sql.SQLException;
import java.util.List;

import cn.estore.dao.ProductDaoImp;
import cn.estore.domain.PageBean;
import cn.estore.domain.Product;

public class ProductService {
    ProductDaoImp dao = new ProductDaoImp();

    //查询所有商品信息
    public List<Product> findAll() throws SQLException {
        return dao.findAll();
    }

    //通过id查找唯一商品
    public Product findById(int id) throws SQLException {
        return dao.findById(id);
    }

    //按条件查询
    /*field为字段名称、msg为为字段值*/
    public List<Product> simpleSelect(String field, String msg) throws SQLException {
        return dao.simpleSelect(field, msg);
    }

    //根据所传值得到在该分类的商品
    public List<Product> findByCode(String code) throws SQLException {
        return dao.findByCode(code);
    }

    //
    public PageBean findByPageByCode(int pageNum, int currentPage, String code) {
        PageBean pb = new PageBean();
        try {
            List<Product> pro = dao.findByPageByCode(pageNum, currentPage, code);
            // 查询总条数:
            int totalCount = dao.findCountByCode(code);
            // 得到总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentPage);
            pb.setTotalCount(totalCount); // 封装总条数
            pb.setTotalPage(totalPage);// 封装总页数
            pb.setPro(pro);// 封装当前页数据.
            pb.setCurrentPage(currentPage); // 封装每页条数
            pb.setPageNum(pageNum);// 封装当前页码
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return pb;
    }

}
