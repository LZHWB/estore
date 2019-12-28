package cn.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Product;


@WebServlet("/RemoveSelectProductFromCartServlet")
public class RemoveSelectProductFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] id = request.getParameterValues("id");

        Map<Product, Integer> cart = (Map<Product, Integer>) request
                .getSession().getAttribute("cart");

        for (int i = 0; i < id.length; i++) {
            Product p = new Product();
            p.setId(Integer.parseInt(id[i]));
            cart.remove(p);
        }

        response.sendRedirect(request.getContextPath() + "/showcart.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
