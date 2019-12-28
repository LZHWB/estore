package cn.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.estore.domain.User;
import cn.estore.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.得到所有请求参数，封装到User对象中.
        User user = new User();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            BeanUtils.populate(user, request.getParameterMap());
            //会遍历map<key, value>中的key，如果bean中有这个属性，就把这个key对应的value值赋给bean的属性。
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 2.校验用户名密码数据是否为空为空，
        //如果用户名密码为空，即Map集合中拥有返回的错误信息，即Map的集合有大小
        Map<String, String> map = user.validation();

        if (map.size() != 0) {
            request.setAttribute("map", map);
            request.getRequestDispatcher("home.jsp").forward(request,response);
            return;
        }

        // 3.调用service中登录的方法
        UserService service = new UserService();
        User user1 = service.login(username, password);
        if(user1!=null){
            // 登录成功
            request.getSession().invalidate();//先销毁session。

            request.getSession().setAttribute("user", user1);// 登录成功，将user存储到session中.

            response.sendRedirect(request.getContextPath()+"/index.jsp"); // 请求转发只能在站内跳转,登录成功加上用户信息
        }else{
            response.sendRedirect(request.getContextPath()+"/error/login_error.jsp");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
