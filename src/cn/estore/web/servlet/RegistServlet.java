package cn.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.estore.domain.User;
import cn.estore.service.UserService;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* 得到所有请求参数，封装到User对象中*/
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service完成注册操作
        UserService service = new UserService();

        if(service.regist(user)>0)
        {// 1注册成功
            response.sendRedirect(request.getContextPath()
                    + "/regist_success.jsp");
        }else{
            // 2注册失败
            request.setAttribute("regist.message","注册失败");
            request.getRequestDispatcher("/error/registuser_error.jsp").forward(request,
                    response);
            return;
        }

    }
}
