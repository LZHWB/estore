package cn.estore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Product;
import cn.estore.domain.User;
import cn.estore.service.ProductService;

@WebServlet("/ProductFindByIdServlet")
public class ProductFindByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取输入信息
        String id = request.getParameter("id");

        //调用service中查询所有方法
        ProductService service = new ProductService();

        try {
            Product pro = service.findById(Integer.parseInt(id));

            request.getSession().setAttribute("pro", pro);

            User user = (User) request.getSession().getAttribute("user");

            if (user == null || user.getRole().equals("user")) {
                request.getRequestDispatcher("/productInfo_user.jsp").forward(request, response);
                return;
            }
            //管理员
//            request.getRequestDispatcher("/productInfo_admin.jsp").forward(request, response);
            return;

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
