package cn.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.estore.domain.Order;
import cn.estore.service.OrderService;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.得到实体类对象，将数据封装至实体类中
        Order order=new Order();
        try {
            BeanUtils.populate(order, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        OrderService service=new OrderService();
        if(service.addOrder(order)>0) {
            response.sendRedirect(request.getContextPath()
                    + "/index.jsp");
        }else{
            request.setAttribute("addOrder.message", "订单生成失败");
            request.getRequestDispatcher("/error/addOrder_error.jsp").forward(request,
                    response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
