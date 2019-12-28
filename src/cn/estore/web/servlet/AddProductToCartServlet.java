package cn.estore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.estore.domain.Product;
import cn.estore.domain.User;
import cn.estore.service.ProductService;

@WebServlet("/AddProductToCartServlet")
public class AddProductToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProductToCartServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.得到商品id
        String pid = request.getParameter("pid");
        User user= (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/pleaseLogin.jsp");
        }else {
            // 2.根据id查询商品
            ProductService service = new ProductService();
            try {
                Product p = service.findById(Integer.parseInt(pid));
                // 3.将商品添加到购物车
                HttpSession session = request.getSession();
                // 从session中获取购物车
                Map<Product, Integer> cart = (Map<Product, Integer>) session
                        .getAttribute("cart");
                // 如果cart为null,说明，没有购物车，是第一次购物
                if (cart == null) {
                    // 创建出购物车
                    cart = new HashMap<Product, Integer>();
                }
                // 判断购物车中是否有当前要买的商品
                Integer count = cart.get(p);
                if (count == null) {
                    // 如果为null,说明购物车中没有这个商品，这时商品的数量就为1
                    count = 1;
                } else {
                    // 如果不为null,说明购物车中有这个商品，这时，就将商品的数量+1
                    count += 1;
                }
                // 将商品存储到购物车中
                cart.put(p, count);
                // 将购物车存储到session中.
                session.setAttribute("cart", cart);

                response.sendRedirect(request.getContextPath() + "/addProductToCartSuccessfull.jsp");
                return;

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
